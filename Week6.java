import project20280.list.SinglyLinkedList;

public class Week6 {
/* 
Q1
    - return {15, 1, 2, 34, 9, 3, 11, 6, 19, 5, 12}
    - return {15, 1, 2, 34, 9, 3, 11, 6, 19, 5, 12}
    - return {15, 1, 2, 34, 11, 3, 9, 6, 19, 5, 12}
    - return {15, 1, 2, 6, 11, 3, 9, 34, 19, 5, 12}
    - return {15, 1, 19, 6, 11, 3, 9, 34, 2, 5, 12}
    - return {15, 5, 19, 6, 11, 3, 9, 34, 2, 1, 12}

Q2
    - return (((1 + 0) + 1) + (1 + 0)) + ((1 + 0) + 1) = 5
    - return ((1 + 0) + 1) + (1 + 0) = 3
    - return (1 + 0) + 1 = 2
    - return 1 + 0 = 1
    - return 1
    - return 0
    */

    public static int recurFibonacci(int i) {
        if (i < 2) return i;
        else return recurFibonacci(i - 2) + recurFibonacci(i - 1);
    }

/*
Q3
    - return ((((((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0) + 1) + ((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0)) + (((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0) + 1) + ((1 + 0 + 0) + 1 + 0)) + ((((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0) + 1) + ((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0)) + (((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0) + 1)) + (((((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0) + 1) + ((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0)) + (((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0) + 1) + ((1 + 0 + 0) + 1 + 0)) + ((((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0) + 1) + ((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0)) = 44
    - return (((((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0) + 1) + ((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0)) + (((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0) + 1) + ((1 + 0 + 0) + 1 + 0)) + ((((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0) + 1) + ((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0)) + (((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0) + 1) = 24
    - return ((((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0) + 1) + ((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0)) + (((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0) + 1) + ((1 + 0 + 0) + 1 + 0) = 13
    - return (((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0) + 1) + ((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0) = 7
    - return ((1 + 0 + 0) + 1 + 0) + (1 + 0 + 0) + 1 = 4
    - return (1 + 0 + 0) + 1 + 0 = 2
    - return 1 + 0 + 0 = 1
    - return 1
    - return 0
    - return 0
*/

    public static int recurTribonacci(int i) {
        if (i == 0) return 0;
        if (i == 1) return 0;
        if (i == 2) return 1;
        else return recurTribonacci(i - 3) + recurTribonacci(i - 2) + recurTribonacci(i - 1);
    }

/*
Q4
    Nested Recursion

    - return 87 + 11
    -
    -
    - return -11
 */

    public static int M(int n) {
        System.out.println(n);
        if (n > 100)return n - 10;
        if (n <= 100) return M(M(n + 11));
        else return -1;
    }

//Q5

    public static int Foo(int x) {
        if (x / 2 == 0) {
            System.out.println(x);
            return 0;
        }
            Foo(x / 2);
            System.out.println(x % 2);
            return x;
    }

//a) prints input in binary
//b) 100110100100

//Q6
    /*
    a)
    if node is null return
    recursive call to next node
    print current node

    b) in SinglyLinkedList
    */

//Q7
/*
    a)
    create new linked list
    new head = old head
    call Node signature helper

    if node is null return
    next = recursive call with og next


*/

//Q8
    public static int mystery(int n, int a , int d) {
    if(n == 1) { return a; }
    else { return d + mystery(n - 1, a, d); }
    }
/*
    return 4 + (4)
    return 4
*/

//Q9 in LinkedBinaryTree

    public static void main(String[] args) {
        System.out.println(recurFibonacci(5));
        System.out.println(recurTribonacci(9));
        System.out.println(M(87));
        System.out.println(Foo(2468));
        System.out.println(mystery(2,4,4));
    }
}