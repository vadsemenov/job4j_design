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
        return findByPredicate(n -> n.children.size() > 2).isEmpty();
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> parentOp = findBy(parent);
        if (!parentOp.isEmpty()) {
            Optional<Node<E>> childOp = findBy(child);
            if (childOp.isEmpty()) {
                parentOp.get().children.add(new Node<E>(child));
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(n -> n.value.equals(value));
    }


    public Optional<Node<E>> findByPredicate(Predicate<Node<E>> predicate) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
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