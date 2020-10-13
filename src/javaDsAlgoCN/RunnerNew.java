package javaDsAlgoCN;

import java.util.Scanner;

class LinkedListNode<T> {
	public T data;
	public LinkedListNode<T> next;

	public LinkedListNode(T data) {
		this.setData(data);
		this.next = null;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}

public class RunnerNew {
	private static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {
//		SolutionNew.printReverseRecursive(input());
		print(SolutionNew.removeElements(takeInput(), 6));

	}

	public static LinkedListNode<Integer> takeInput() {
		int data = s.nextInt();
		
		LinkedListNode<Integer> head = null;
		LinkedListNode<Integer> tail = null;
		while (data!=-1) {
			LinkedListNode<Integer> newNode = new LinkedListNode<Integer>(data);
			if (head == null) {
				head = newNode;
				tail = newNode;
			} else {
				tail.next = newNode;
				tail = newNode;
			}
			data = s.nextInt();
		}
		return head;
	}

	public static void print(LinkedListNode<Integer> head) {
		while (head != null) {
			System.out.print(head.getData() + " ");
			head = head.next;
		}
	}
}

class SolutionNew {
	public static LinkedListNode<Integer> append(LinkedListNode<Integer> head, int n) {
        LinkedListNode<Integer> temp = head;
        LinkedListNode<Integer> nHead = null;
        int llLen = 0;
        int frmFrnt = 0;
        int i = 0;
        while(temp.next != null) {
            llLen++;
            temp = temp.next;
        }
        temp= head;
        frmFrnt = llLen - n;
        while (i != frmFrnt) {
            temp = temp.next;
            i++;
        }
        nHead = temp.next;
        temp.next = null;
        temp = nHead;
        while(temp.next != null) {
            temp = temp.next;
        }
        temp.next = head;
        return nHead;
    }
	public static LinkedListNode<Integer> removeDuplicates(LinkedListNode<Integer> head) {
		LinkedListNode<Integer> temp = head;
		LinkedListNode<Integer> save = null;

		while(temp != null) {
			save = temp;
			if (temp.next == null){
				break;
			}
			temp = temp.next;
			while(temp.data.equals(save.data)){
				if (temp.next == null) {
					temp =temp.next;
					break;
				}
				temp = temp.next;
			}
			save.next = temp;
		}
		return head;
	}
	public static void printReverseRecursive(LinkedListNode<Integer> root) {
		if (root == null) {
			return;
		}
		printReverseRecursive(root.next);
		System.out.println(root.data);
	}
	public static LinkedListNode<Integer> mergeTwoListRec(LinkedListNode<Integer> head1, LinkedListNode<Integer> head2) {
		/*LinkedListNode<Integer> head = null;
		LinkedListNode<Integer> tail = null;
		if (head1 == null) return head2;
		if (head2 == null) return head1;

		if (head1.data < head2.data) {
			head = head1;
			tail = head1;
			head1 = head1.next;
		} else {
			head = head2;
			tail = head2;
			head2 = head2.next;
		}
		while (head1 != null && head2 != null) {
			if (head1.data < head2.data) {
				tail.next = head1;
				head1 = head1.next;
				tail = tail.next;
			} else {
				tail.next = head2;
				head2 = head2.next;
				tail = tail.next;
			}
		}
		if (head1 != null) {
			while (head1 != null) {
				tail.next = head1;
				head1 = head1.next;
				tail = tail.next;
			}
		}
		if (head2 != null) {
			while (head2 != null) {
				tail.next = head2;
				head2 = head2.next;
				tail = tail.next;
			}
		}
		return head;*/
		LinkedListNode<Integer> result = null;
		if (head1 == null) return head2;
		if (head2 == null) return head1;

		if(head1.data < head2.data) {
			result = head1;
			result.next = mergeTwoListRec(head1.next, head2);
		} else {
			result = head2;
			result.next = mergeTwoListRec(head1, head2.next);
		}
		return result;
	}
	public static LinkedListNode<Integer> skipMdeleteN(LinkedListNode<Integer> head, int M, int N) {
		int tempM = M;
		int tempN = N;
		LinkedListNode<Integer> tempH = head;
		LinkedListNode<Integer> tempS = null;
		if (tempM == 0) {
			return null;
		}
		while(tempH != null) {
			while (tempM > 1) {
				tempH = tempH.next;
				tempM--;
			}
			while (tempN > 0 && tempH.next != null) {
				tempS = tempH.next;
				tempH.next = tempH.next.next;
				tempS.next = null;
				tempN--;
			}
			tempH = tempH.next;
			tempM = M;
			tempN = N;
		}
		return head;
	}
	public static  LinkedListNode<Integer> swap_nodes_my(LinkedListNode<Integer> head,int i,int j){
		int lli = 0;
		int llj = 0;
		if (i>j) {
			i = i+j;
			j = i - j;
			i = i - j;
		}
		LinkedListNode<Integer> tempH = head;
		LinkedListNode<Integer> fHead = head;
		LinkedListNode<Integer> sHead = head;
		LinkedListNode<Integer> fSave = null;
		LinkedListNode<Integer> nxtSave_f = null;
		LinkedListNode<Integer> nxtSave_s = null;
		if (i == j) {
			return head;
		}
		if (i == 0) {
			while(llj < (j-1)) {
				sHead = sHead.next;
				llj++;
			}
			fSave = head;
			head = sHead.next;
			nxtSave_s = sHead.next.next;
			head.next = fSave.next;
			sHead.next = fSave;
			sHead.next.next = nxtSave_s;

			return head;
		}

		while (lli < (i-1)){
			fHead = fHead.next;
			lli++;
		}
		while (llj < (j-1)) {
			sHead = sHead.next;
			llj++;
		}
		fSave = fHead.next;
		nxtSave_f = fHead.next.next;
		nxtSave_s = sHead.next.next;
		if (fHead.next.next == sHead.next){
			fHead.next = sHead.next;
			fHead.next.next = sHead;
			sHead.next = nxtSave_s;
		}else{
			fHead.next = sHead.next;
			fHead.next.next = nxtSave_f;
			sHead.next = fSave;
			sHead.next.next = nxtSave_s;
		}
		return head;
	}
	public static  LinkedListNode<Integer> swap_nodes(LinkedListNode<Integer> head,int i,int j){
		int lli = 0;
		int llj = 0;
		LinkedListNode<Integer> previ = null;
		LinkedListNode<Integer> prevj = null;
		LinkedListNode<Integer> curri = head;
		LinkedListNode<Integer> currj = head;
		if (i>j) {
			i = i+j;
			j = i - j;
			i = i - j;
		}
		while (lli < i){
			previ = curri;
			curri = curri.next;
			lli++;
		}
		while (llj < j) {
			prevj = currj;
			currj = currj.next;
			llj++;
		}
		if (previ != null) {
			previ.next = currj;
		} else {
			head = currj;
		}
		if (prevj != null) {
			prevj.next = curri;
		} else {
			head = curri;
		}
		LinkedListNode<Integer> temp = curri.next;
		curri.next = currj.next;
		currj.next = temp;

		return head;
	}
	public static LinkedListNode<Integer> bubbleSort(LinkedListNode<Integer> head ) {
		int size = 0;
		LinkedListNode<Integer> current = head;
		LinkedListNode<Integer> temp_current = head;
		LinkedListNode<Integer> previous = null;
		LinkedListNode<Integer> nxt;
		while (temp_current != null) {
			size++;
			temp_current = temp_current.next;
		}
		if (size > 1) {
			boolean wasChanged;

			do {
				current = head;
				previous = null;
				if (head == null) {
					break;
				}
				nxt = head.next;
				wasChanged = false;

				while ( nxt != null ) {
					if (current.data > nxt.data) {
						wasChanged = true;
						if ( previous != null ) {
							LinkedListNode<Integer> sig = nxt.next;

							previous.next = nxt;
							nxt.next = current;
							current.next = sig;
						} else {
							LinkedListNode<Integer> sig = nxt.next;
							head = nxt;
							nxt.next = current;
							current.next = sig;
						}

						previous = nxt;
						nxt = current.next;
					} else {
						previous = current;
						current = nxt;
						nxt = nxt.next;
					}
				}
			} while( wasChanged );
		}
		return head;
	}
	public static LinkedListNode<Integer> removeElements(LinkedListNode<Integer> head, int val) {
		LinkedListNode<Integer> temp = head;
		LinkedListNode<Integer> prev = null;
		while(temp != null) {
			if (temp.data == val && temp == head) {
				head = head.next;
				temp = head;
				prev = temp;
			} else if (temp.data == val) {
				prev.next = temp.next;
				temp = temp.next;
			} else {
				prev = temp;
				temp = temp.next;
			}
		}
		return head;
	}
    public static LinkedListNode<Integer> mergeTwoList(LinkedListNode<Integer> head1, LinkedListNode<Integer> head2) {
        LinkedListNode<Integer> head = null;
        LinkedListNode<Integer> tail = null;
        if (head1 == null) return head2;
        if (head2 == null) return head1;

        if (head1.data < head2.data) {
            head = head1;
            tail = head1;
            head1 = head1.next;
        } else {
            head = head2;
            tail = head2;
            head2 = head2.next;
        }
        while (head1 != null && head2 != null) {
            if (head1.data < head2.data) {
                tail.next = head1;
                head1 = head1.next;
                tail = tail.next;
            } else {
                tail.next = head2;
                head2 = head2.next;
                tail = tail.next;
            }
        }
        if (head1 != null) {
            while (head1 != null) {
                tail.next = head1;
                head1 = head1.next;
                tail = tail.next;
            }
        }
        if (head2 != null) {
            while (head2 != null) {
                tail.next = head2;
                head2 = head2.next;
                tail = tail.next;
            }
        }
        return head;
    }
    public static LinkedListNode<Integer> getMiddle(LinkedListNode<Integer> head) {
        LinkedListNode<Integer> sp = head;
        LinkedListNode<Integer> fp = head.next;
        while (fp != null) {
            if (fp.next == null) {
                return sp;
            }
            fp = fp.next.next;
            sp = sp.next;
        }
        return sp;
    }
    public static LinkedListNode<Integer> mergeSort(LinkedListNode<Integer> head) {
        LinkedListNode<Integer> frstHlfHead = head;
        LinkedListNode<Integer> scndHlfHead = null;
        LinkedListNode<Integer> nxtOfMid    = null;
        LinkedListNode<Integer> midOfList	= null;
        LinkedListNode<Integer> sortedList  = null;
        if (head == null || head.next == null) {
            return head;
        }

        midOfList  = getMiddle(head);
        nxtOfMid   = midOfList.next;

        midOfList.next = null;

        frstHlfHead = mergeSort(head);
        scndHlfHead = mergeSort(nxtOfMid);

        sortedList  = mergeTwoList(frstHlfHead, scndHlfHead);

        return sortedList;

    }
}

