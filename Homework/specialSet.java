class SpecialSet<E> extends AbstractSet<E> implements Set<E> {
    private final List<E> list = new ArrayList<>();
    private final Map<E,Integer> indexMap = new HashMap<>();

    @Override
    public boolean add(E e) {
        if (contains(e)) return false;
        indexMap.put(e, list.size());
        list.add(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Integer indexBoxed = indexMap.remove(o);
        if (indexBoxed == null) return false;
        int index = indexBoxed;
        int last = list.size() - 1;
        E element = list.remove(last);
        if (index != last) {
            indexMap.put(element, index);
            list.set(index, element);
        }
        return true;
    }

    public E removeRandom() {
        E element = list.get((int)(Math.random() * size()));
        remove(element);
        return element;
    }

    @Override
    public boolean contains(Object o) {
        return indexMap.containsKey(o);
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public int size() {
        return list.size();
    }
}
