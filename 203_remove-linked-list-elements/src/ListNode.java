/**
 * Created by chengbin on 2022-04-06.
 */
public class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "val:" + val + ", next:[" + (next==null?"null":next.toString()) + "]";
    }
}
