
/**
 * Created by Hyman on 2016/10/17.
 */

/**********************************************************************************
 *
 * Sort a linked list using insertion sort.
 *
 **********************************************************************************/
public class InsertionSortList {


    public static void main(String[] args) {

        ListNode head = new ListNode(5);
        ListNode head1 = new ListNode(3);
        ListNode head2 = new ListNode(6);
        ListNode head3 = new ListNode(8);
        ListNode head4 = new ListNode(4);
        head.next = head1;
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = null;

        //ListNode node = sort_move(head);
        ListNode node = sort_changePointer(head);

        while(node != null){
            System.out.print(node.val + " ");
            node = node.next;
        }

    }

    /**
     * 执行排序，每找到插入位置后通过修改节点的next指针达到最终有序
     * @param head
     * @return
     */
    private static ListNode sort_changePointer(ListNode head){

        if(head == null || head.next == null) {
            return head;
        }

        ListNode font = new ListNode(0);
        font.next = head;
        ListNode preCur = head;
        ListNode cur = head.next;

        while(cur != null) {

            ListNode search = head;
            ListNode preSearch = font;

            // 找到待插入的位置
            while(search.val<=cur.val && search!=cur) {
                search = search.next;
                preSearch = preSearch.next;
            }

            // 表示cur之前正好有序，则只需移动指针
            if(search == cur) {
                cur = cur.next;
                preCur = preCur.next;

            } else {

                ListNode curSave = cur.next;
                preCur.next = curSave;

                preSearch.next = cur;
                cur.next = search;
                // 如果插入是在head头结点之前，则修改head
                if(search == head) {
                    head = cur;
                }

                cur = curSave;
            }

        }

        return head;
    }


    /**
     * 执行排序，每找到插入位置后将插入位置及以后的元素往后移
     * @param head
     * @return
     */
    private static ListNode sort_move(ListNode head){

        if(head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;

        while(cur != null) {

            ListNode search = head;

            // 找到待插入的位置
            while(search.val<=cur.val && search!=cur) {
                search = search.next;
            }

            //用于移动元素过程中保存上一个值
            int last = search.val;
            ListNode start = search;

            while(search != cur){
                int tmp = search.next.val;
                search.next.val = last;
                last = tmp;
                search = search.next;
            }

            start.val = last;
            cur = cur.next;
        }

        return head;
    }

    /**
     * 节点定义
     */
    static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }
}
