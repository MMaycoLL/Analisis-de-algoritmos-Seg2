package seguimiento;

public class BitonicSort implements AlgoritmoOrdenamiento {

	/*
	 * The parameter dir indicates the sorting direction, ASCENDING or DESCENDING;
	 * if (a[i] > a[j]) agrees with the direction, then a[i] and a[j] are
	 * interchanged.
	 */
	void compAndSwap(int a[], int i, int j, int dir) {
		if ((a[i] > a[j] && dir == 1) || (a[i] < a[j] && dir == 0)) {
			// Swapping elements
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}

	/*
	 * It recursively sorts a bitonic sequence in ascending order, if dir = 1, and
	 * in descending order otherwise (means dir=0). The sequence to be sorted starts
	 * at index position low, the parameter cnt is the number of elements to be
	 * sorted.
	 */
	void bitonicMerge(int a[], int low, int cnt, int dir) {
		if (cnt > 1) {
			int k = cnt / 2;
			for (int i = low; i < low + k; i++)
				compAndSwap(a, i, i + k, dir);
			bitonicMerge(a, low, k, dir);
			bitonicMerge(a, low + k, k, dir);
		}
	}

	/*
	 * This function first produces a bitonic sequence by recursively sorting its
	 * two halves in opposite sorting orders, and then calls bitonicMerge to make
	 * them in the same order
	 */
	void bitonicSort(int a[], int low, int cnt, int dir) {
		if (cnt > 1) {
			int k = cnt / 2;

			// sort in ascending order since dir here is 1
			bitonicSort(a, low, k, 1);

			// sort in descending order since dir here is 0
			bitonicSort(a, low + k, k, 0);

			// Will merge whole sequence in ascending order
			// since dir=1.
			bitonicMerge(a, low, cnt, dir);
		}
	}

	/*
	 * Caller of bitonicSort for sorting the entire array of length N in ASCENDING
	 * order
	 */
	@Override
	public void ordenar(int a[], int n) {
		int up = 1;
		bitonicSort(a, 0, n, up);
	}

}
