package cn.vincent.design.behaviorModel.iterator;

/**
 * 自定义集合
 */
public class MyCollection implements Collection {

    String[] arr = new String[]{
            "a", "b", "c", "d", "e", "f", "g"
    };

    @Override
    public Iterator getIterator() {
        return new MyIterator();
    }

    /**
     * 自定义迭代器
     */
    private class MyIterator implements Iterator {
        int index;

        @Override
        public boolean hasNext() {
            if (index < arr.length) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return arr[index++];
            }
            return null;
        }
    }
}
