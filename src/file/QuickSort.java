package file;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class QuickSort<T extends Comparable> extends RecursiveAction {

	private List<T> data;
	private int leftIndex;
	private int rightIndex;

	public QuickSort(List<T> data) {
		this.data = data;
		this.leftIndex = 0;
		this.rightIndex = data.size() - 1;
	}

	public QuickSort(List<T> data, int left, int right) {
		this.data = data;
		this.leftIndex = left;
		this.rightIndex = right;
	}

	@Override
	protected void compute() {
		if (leftIndex < rightIndex) {
			int pivotIndex = leftIndex + ((rightIndex - leftIndex) / 2);

			pivotIndex = partition(pivotIndex);

			invokeAll(new QuickSort(data, leftIndex, pivotIndex - 1), new QuickSort(
					data, pivotIndex + 1, rightIndex));
		}

	}

	private int partition(int pivotIndex) {
		T pivotValue = data.get(pivotIndex);

		swap(pivotIndex, rightIndex);

		int storeIndex = leftIndex;
		for (int i = leftIndex; i < rightIndex; i++) {
			if (data.get(i).compareTo(pivotValue) < 0) {
				swap(i, storeIndex);
				storeIndex++;
			}
		}

		swap(storeIndex, rightIndex);

		return storeIndex;
	}

	private void swap(int i, int j) {
		if (i != j) {
			T iValue = data.get(i);

			data.set(i, data.get(j));
			data.set(j, iValue);
		}
	}
}