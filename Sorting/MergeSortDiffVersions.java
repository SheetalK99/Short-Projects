package sak170006;
/** Sample starter code for SP9.
 *  @author
 */

import java.util.Arrays;
import java.util.Random;

public class SP9 {
	public static Random random = new Random();
	public static int numTrials = 100;
	public static int T = 20;

	public static void main(String[] args) {
		int n = 64000000;
		int choice = 4;
		if (args.length > 0) {
			n = Integer.parseInt(args[0]);
		}
		if (args.length > 1) {
			choice = Integer.parseInt(args[1]);
		}
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = i;
		}
		Timer timer = new Timer();
		switch (choice) {
		case 1:
			Shuffle.shuffle(arr);
			numTrials = 1;
			insertionSort(arr);
			break;
		case 2:
			for (int i = 0; i < numTrials; i++) {
				Shuffle.shuffle(arr);
				mergeSort1(arr);
			}
			break;
		case 3:
			for (int i = 0; i < numTrials; i++) {
				Shuffle.shuffle(arr);
				mergeSort2(arr);
			}
			break;
			
		case 4:
			for (int i = 0; i < numTrials; i++) {
				Shuffle.shuffle(arr);
				mergeSort3(arr);
			}
			break;
		// etc
		}
		timer.end();
		timer.scale(numTrials);

		System.out.println("Choice: " + choice + "\n" + timer);
	}

	public static void insertionSort(int[] arr) {

		insertionSort(arr, 0, arr.length - 1);

	}

	public static void insertionSort(int[] arr, int p, int r) {
		for (int i = p + 1; i <= r; i++) {
			int tmp = arr[i];
			int j = i - 1;
			while (j >= p && tmp < arr[j]) {
				arr[j + 1] = arr[j];
				j = j - 1;
			}
			arr[j + 1] = tmp;
		}

	}

	public static void mergeSort3(int[] arr) {
		int[] B = new int[arr.length];
		System.arraycopy(arr, 0, B, 0, arr.length);
		mergeSort2(arr, B, 0, arr.length);

	}

	public static void mergeSort3(int[] A, int[] B, int left, int n) {
		// Sort A[left..left+n-1] or B[left .. left+n-1]
		// into A[left .. left+n-1]
		// Pre: A[left .. left+n-1], B[left .. left+n-1]
		// have the same elements

		if (n < T) {
			insertionSort(A, left, left + n - 1);
		} else {

			int Ln = n / 2;
			mergeSort2(B, A, left, Ln);
			mergeSort2(B, A, left + Ln, n - Ln);
			merge2(A, B, left, left + Ln - 1, left + n - 1);
		}

	}

	public static void merge3(int[] A, int[] B, int p, int q, int r) {
		// Merge B[p .. q] and B[q + 1 .. r] into A[p .. r], in sorted order
		// Pre: B[p .. q], and B[q + 1 .. r] are in sorted order

		int i = p;
		int j = q + 1;
		int k = p;

		while (i <= q && j <= r) {
			if (B[i] <= B[j]) {
				A[k++] = B[i++];
			} else {
				A[k++] = B[j++];
			}
		}

		while (i <= q) {
			A[k++] = B[i++];
		}

		while (j <= r) {
			A[k++] = B[j++];
		}
	}

	public static void mergeSort2(int[] arr) {
		int[] B = new int[arr.length];
		mergeSort2(arr, B, 0, arr.length);

	}

	public static void mergeSort2(int[] A, int[] B, int left, int n) {
		// Sort A[ p · · · r ]

		if (n < T) {
			insertionSort(A, left, left + n - 1);
		} else {

			int Ln = n / 2;
			mergeSort2(A, B, left, Ln);
			mergeSort2(A, B, left + Ln, n - Ln);
			merge2(A, B, left, left + Ln - 1, left + n - 1);
		}

	}

	public static void merge2(int[] A, int[] B, int p, int q, int r) {
		// Merge A[p .. q] and A[q + 1 · · · r] into A[p .. r], in sorted order
		// Use B for temporary storage
		// Pre: A[p · · · q], and A[q + 1 · · · r] are in sorted order

		System.arraycopy(A, p, B, p, r - p + 1);

		int i = p;
		int j = q + 1;

		for (int k = p; k <= r; k++) {
			if (j > r || (i <= q && B[i] <= B[j])) {
				A[k] = B[i++];
			} else {
				A[k] = B[j++];
			}
		}
	}

	public static void mergeSort1(int[] arr) {
		mergeSort1(arr, 0, arr.length - 1);

	}

	public static void mergeSort1(int[] A, int p, int r) {
		// Sort A[ p · · · r ]

		if (p < r) {
			int q = (p + r) / 2;
			mergeSort1(A, p, q);
			mergeSort1(A, q + 1, r);
			merge(A, p, q, r);

		}

	}

	public static void merge(int[] A, int p, int q, int r) {
		int[] L = new int[q - p + 1];
		int[] R = new int[r - q];
		System.arraycopy(A, p, L, 0, q - p + 1);

		System.arraycopy(A, q + 1, R, 0, r - q);

		int i = 0;
		int j = 0;

		for (int k = p; k <= r; k++) {
			if (j >= R.length || (i < L.length && L[i] <= R[j])) {
				A[k] = L[i++];
			} else {
				A[k] = R[j++];
			}
		}
	}

	/**
	 * Timer class for roughly calculating running time of programs
	 * 
	 * @author rbk Usage: Timer timer = new Timer(); timer.start(); timer.end();
	 *         System.out.println(timer); // output statistics
	 */

	public static class Timer {
		long startTime, endTime, elapsedTime, memAvailable, memUsed;
		boolean ready;

		public Timer() {
			startTime = System.currentTimeMillis();
			ready = false;
		}

		public void start() {
			startTime = System.currentTimeMillis();
			ready = false;
		}

		public Timer end() {
			endTime = System.currentTimeMillis();
			elapsedTime = endTime - startTime;
			memAvailable = Runtime.getRuntime().totalMemory();
			memUsed = memAvailable - Runtime.getRuntime().freeMemory();
			ready = true;
			return this;
		}

		public long duration() {
			if (!ready) {
				end();
			}
			return elapsedTime;
		}

		public long memory() {
			if (!ready) {
				end();
			}
			return memUsed;
		}

		public void scale(int num) {
			elapsedTime /= num;
		}

		public String toString() {
			if (!ready) {
				end();
			}
			return "Time: " + elapsedTime + " msec.\n" + "Memory: " + (memUsed / 1048576) + " MB / "
					+ (memAvailable / 1048576) + " MB.";
		}
	}

	/**
	 * @author rbk : based on algorithm described in a book
	 */

	/* Shuffle the elements of an array arr[from..to] randomly */
	public static class Shuffle {

		public static void shuffle(int[] arr) {
			shuffle(arr, 0, arr.length - 1);
		}

		public static <T> void shuffle(T[] arr) {
			shuffle(arr, 0, arr.length - 1);
		}

		public static void shuffle(int[] arr, int from, int to) {
			int n = to - from + 1;
			for (int i = 1; i < n; i++) {
				int j = random.nextInt(i);
				swap(arr, i + from, j + from);
			}
		}

		public static <T> void shuffle(T[] arr, int from, int to) {
			int n = to - from + 1;
			Random random = new Random();
			for (int i = 1; i < n; i++) {
				int j = random.nextInt(i);
				swap(arr, i + from, j + from);
			}
		}

		static void swap(int[] arr, int x, int y) {
			int tmp = arr[x];
			arr[x] = arr[y];
			arr[y] = tmp;
		}

		static <T> void swap(T[] arr, int x, int y) {
			T tmp = arr[x];
			arr[x] = arr[y];
			arr[y] = tmp;
		}

		public static <T> void printArray(T[] arr, String message) {
			printArray(arr, 0, arr.length - 1, message);
		}

		public static <T> void printArray(T[] arr, int from, int to, String message) {
			System.out.print(message);
			for (int i = from; i <= to; i++) {
				System.out.print(" " + arr[i]);
			}
			System.out.println();
		}
	}
}
