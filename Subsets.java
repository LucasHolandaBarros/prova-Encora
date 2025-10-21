class MySet {
    private int[] elements;
    private int size;

    /*
    * This function below is the constructor of Class MySet, where the elements are initialized
    * */
    public MySet() {
        elements = new int[4];
        size = 0;
    }

    /*
    * This function below is analyzing if there are any elements already included in the set,
    * for this it is necessary to use the method contains.
    * */
    public boolean add(int e) {
        if (!contains(e)) {
            elements[size++] = e;
            return true;
        }
        return false;
    }

    /*
    * This function below exists to verify if any elements are duplicated,
    * it compares the new element to each another element already established on the set
    * */
    public boolean contains(int e) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == e) return true;
        }
        return false;
    }

    /*
    * This function below return the size of the set
    * */
    public int size() {
        return size;
    }

    /*
    * This function below is used to transform the Set in a generic array
    * */
    public int[] toArray() {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = elements[i];
        return arr;
    }

    /*
    * This function below just print the elements how it should be printed,
    * using the best format to do it
    * */
    public void print() {
        System.out.print("{ ");
        for (int i = 0; i < size; i++) {
            System.out.print(elements[i]);
            if (i < size - 1) System.out.print(", ");
        }
        System.out.print(" }");
    }
}

public class Subsets {

    /*
    * The function below is the most important one, it is using all the functions implemented in MySet Class
    * */
    public static MySet[] getSubSets(MySet A) {
        // The variable n represents the size of the set A
        int n = A.size();
        // The variable total is the maximum value that a Set can have in numbers of subsets (2^n)
        int total = 1 << n;
        // The variable MySet is the group of subsets
        MySet[] result = new MySet[total];
        // This line below is transforming the Set A to an array
        int[] arr = A.toArray();

        // This for below is running all the subsets of A, the maximum number of subsets
        for (int i = 0; i < total; i++) {
            MySet subset = new MySet();
            // Verify which elements are possible to add in the subset
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    subset.add(arr[j]);
                }
            }
            result[i] = subset;
        }

        // Ordering the subsets by size
        sortBySize(result);

        return result;
    }

    /*
    * This function below will sort the subsets by their specific size
    * using a bubble sort method
    * */
    public static void sortBySize(MySet[] sets) {
        int n = sets.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sets[j].size() > sets[j + 1].size()) {
                    MySet temp = sets[j];
                    sets[j] = sets[j + 1];
                    sets[j + 1] = temp;
                }
            }
        }
    }

    /*
    * The main below is just an example to test the integrability of the code
    * */
    public static void main(String[] args) {
        MySet A = new MySet();
        A.add(1);
        A.add(2);
        A.add(3);
        A.add(4);

        MySet[] subsets = getSubSets(A);

        System.out.println("A´s subsets ordered by size:");
        for (int i = 0; i < subsets.length; i++) {
            subsets[i].print();
            System.out.println();
        }
    }
}
