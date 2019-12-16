import java.util.ArrayList;
import java.util.List;

public class LeeCode {
    /**
     * 到最近的人的最大距离 0代表没人，1代表有人
     *
     * 遍历数组，找出每个座位左边最近和右边最近的人，更新当前空位到最近的人的距离
     * 使用 prev 记录 i 最左边第一个有人的位置，future 记录 i 最右边第一个有人的位置。
     * 座位 i 到最近的人的距离为 min(i - prev, future - i)。
     * 另外有一种特殊情况，如果座位 i 左边没有人，则认为到左边第一个人的距离是无限大，右边同理。
     *
     * @param seats
     * @return
     */
    public int maxDistToClosest(int[] seats){
        int N = seats.length;
        int prev = -1, future = 0;
        int ans = 0;
        for(int i = 0 ; i <N ; i ++){
            if(seats[i] == 1){
                prev =  i;
            }else{
                while(future < N && seats[future] == 0 || future <i){
                    future++;
                }
                int left = prev == -1?N:i-prev;
                int right = future == N?N :future-i;
                ans = Math.max(ans,Math.min(left,right));
            }
        }
        return ans  ;
    }
    /**
     * 给定字符串 S 和单词字典 words, 求 words[i] 中是 S 的子序列的单词个数。
     * @param S 字符串
     * @param words 单词的数组
     * @return  子序列个数
     */
    public int numMatchingSubseq(String S, String[] words) {
        int count = 0 ;
        for(int i = 0 ; i<words.length ; i++){
            if(isSubString(S,words[i].toCharArray()))
                count++;
        }
        return count;
    }

    private boolean isSubString(String s, char[] chars) {
        int fromIndex = 0 ;
        for(int i = 0 ; i < chars.length ; i++){
            //因为是字典排序 所以可以根据索引进行查找
            int index =s.indexOf(chars[i],fromIndex);
            if(index == -1)
                return false;
            fromIndex = index+1;
        }
        return true;
    }

    /**
     * 较大分组的位置
     * 我们称所有包含大于或等于三个连续字符的分组为较大分组。找到每一个较大分组的起始和终止位置。
     * 最终结果按照字典顺序输出
     *
     * 双指针进行判断是否两个元素之间的距离大于等于3
     * @param S 字符串
     * @return 链表
     */
    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> ans = new ArrayList<>();
        int start = 0;
        int end = 0;
        while(end<S.length()){
            List<Integer> list = new ArrayList<>();
            while(end < S.length()&&S.charAt(end) == S.charAt(start)){
                end++;
            }
            if(end - start -1>=2){
                list.add(start);
                list.add(end-1);
                ans.add(list);
            }
            start = end;
        }
        return ans;
    }
    public static void main(String[] args) throws Exception{
        String s  = "123";
        String s1= new String("123");
        String s2 = new String("123");
        System.out.println(s ==s1);
        System.out.println( s== s2);
        System.out.println(s1 == s2);
        System.out.println(s.equals("123"));
        System.out.println(s1.equals("123"));

        LeeCode l = new LeeCode();
        System.out.println(l.largeGroupPositions("abcdddeeeeaabbbcd"));
    }
}
