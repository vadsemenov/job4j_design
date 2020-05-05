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
        for (int i = 0; i < mem.size(); i++) {
            if (id.equals(mem.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean replace(String id, T model) {
        int index = indexOf(id);
        if (index == -1) {
            return false;
        }
        mem.set(index, model);
        return true;
    }

    @Override
    public boolean delete(String id) {
        int index = indexOf(id);
        if (index == -1) {
            return false;
        }
        mem.remove(index);
        return true;
    }

    @Override
    public T findById(String id) {
        int index = indexOf(id);
        if (index == -1) {
            return null;
        }
        return mem.get(index);
    }

}