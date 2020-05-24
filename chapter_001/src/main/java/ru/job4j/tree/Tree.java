package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }


    @Override
    public boolean isBinary() {
        boolean rsl = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);

        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.children.size() > 2) {
                rsl = false;
                break;
            }
            data.addAll(el.children);
        }

        return rsl;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);

        Node<E> el = findBy(parent).get();
        if (el != null) {
            el.children.add(new Node<E>(child));
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
//      Почему так не получается?
//      rsl = data.stream().filter(el -> el.value.equals(value)).findFirst();

        Predicate<Node<E>> predicate = (Node<E> el) -> el.value.equals(value);

        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (predicate.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }


        return rsl;
    }
}