import java.io.SequenceInputStream;

public class Main {
	
	public static void main(String[] args) {
		SequentialList seqList = new SequentialList();
		for (int i = 20; i < 40; i++) {
			seqList.add(i);
		}
		SequentialList second = seqList.copy();
		second.insert(111, 6);
		System.out.println(second);
		System.out.println(seqList);
		System.out.println(second.isPalindrome());
		SequentialList reversed = second.reverse();
		System.out.println(reversed);
		SequentialList palindrome = new SequentialList();
		palindrome.add(1);
		System.out.println(palindrome.isPalindrome());
		palindrome.add(2);
		palindrome.add(1);
		System.out.println(palindrome.isPalindrome());
		palindrome.add(1);
		System.out.println(palindrome.isPalindrome());
		
		
		SequentialList list1 = new SequentialList();
		for (int i = 0; i < 10; i = i + 2) {
			list1.add(i);
		}
		SequentialList list2 = new SequentialList();
		for (int i = 1; i < 10; i = i + 2) {
			list2.add(i);
		}
		System.out.println(list1);
		
		System.out.println(list1.sortedMerge(list2));
		SequentialList list = new SequentialList();
		for (int i = 0; i < 10; i++) {
			list.add(i);
			list.add(i-1);
		}
		System.out.println(list);
		System.out.println(list.removeDuplicates());
		SequentialList list1Splitted = list1.splitAt(2);
		System.out.println(list1);
		System.out.println(list1Splitted);
	}

}

