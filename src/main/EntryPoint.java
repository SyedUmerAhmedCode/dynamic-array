package main;

public class EntryPoint {

	public static void main(String[] args) {
		DynamicArray<Integer> dynamicArray=new DynamicArray<Integer>(5);
		dynamicArray.add(1);
		dynamicArray.add(2);
		dynamicArray.add(3);
		dynamicArray.add(4);
		
		System.out.println(dynamicArray.toString());
	}

}
