package ru.bashedu.railwaydiagnostics.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public abstract class BaseDao<T> {
    @Getter
    protected final String tableName;
    protected final JdbcTemplate jdbcTemplate;
    protected final BaseObjectMapper<T> objectMapper;

    public T save(T object) {
        return save(object, false);
    }

    public T tryToSave(T object) {
        return save(object, true);
    }

    private T save(T object, boolean onConflictDoNothing) {
        Map<String, Object> map = objectMapper.mapObject(object);
        List<String> fieldList = new ArrayList<>(map.keySet());
        String fields = String.join(", ", fieldList);
        Object[] params = fieldList.stream().map(map::get).toArray();
        String questionSymbols = fieldList.stream().map(x -> "?").collect(Collectors.joining(", "));
        return jdbcTemplate.query("" +
                "INSERT INTO " + tableName + "(" + fields + ") " +
                "VALUES (" + questionSymbols + ") " +
                (onConflictDoNothing ? "ON CONFLICT DO NOTHING " : "") +
                "RETURNING * ",
            objectMapper, params).stream().findFirst().orElse(null);
    }

    public void saveAll(Collection<T> objects) {
        saveAll(objects, true);
    }

    public void saveAll(Collection<T> objects, boolean onConflictDoNothing) {
        if (objects == null || objects.isEmpty()) {
            return;
        }
        List<String> fieldList = new ArrayList<>(objectMapper.mapObject(objects.iterator().next()).keySet());
        String fields = String.join(", ", fieldList);
        List<Object[]> params = objects.stream()
            .map(o -> {
                val map = objectMapper.mapObject(o);
                return fieldList.stream().map(map::get).toArray();
            })
            .collect(Collectors.toList());
        String questionSymbols = fieldList.stream().map(x -> "?").collect(Collectors.joining(", "));
        jdbcTemplate.batchUpdate("" +
            "INSERT INTO " + tableName + "(" + fields + ") " +
            "VALUES (" + questionSymbols + ") " +
            (onConflictDoNothing ? "ON CONFLICT DO NOTHING " : " "), params);
    }
    protected List<T> getByField(String fieldName, Object value) {
        return jdbcTemplate.query(String.format("SELECT * FROM %s WHERE %s=?", getTableName(), fieldName), objectMapper, value);
    }
}
