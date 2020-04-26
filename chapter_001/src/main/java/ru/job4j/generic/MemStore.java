package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        this.mem.add(model);
    }

    public int indexOf(String id) {
        for (T item : this.mem) {
            if (id.equals(item.getId())) {
                return mem.indexOf(item);
            }
        }
        return -1;
    }

    @Override
    public boolean replace(String id, T model) {
        if (mem.set(indexOf(id), model) != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        if (mem.remove(indexOf(id)) != null) {
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {

        return mem.get(indexOf(id));
    }

}