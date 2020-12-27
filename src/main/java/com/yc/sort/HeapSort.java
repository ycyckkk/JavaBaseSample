package com.yc.sort;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 * 上浮：当一个节点比父节点大，那么需要交换这个两个节点。交换后还可能比它新的父节点大，因此需要不断地进行比较和交换操作
 * 下沉：一个节点如果有两个子节点，应当与两个子节点中最大那个节点进行交换
 * 删除最大元素：从数组顶端删除最大的元素，并将数组的最后一个元素放到顶端，并让这个元素下沉到合适的位置。
 * 插入元素：将新元素放到数组末尾，然后上浮到合适的位置。
 * @Author yucheng
 * @Date 2020/12/25 20:53
 */
public class HeapSort {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] arr = {72, 6, 57, 88, 60, 42, 83};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {
        if (arr == null) {
            return;
        }
        int len = arr.length;
        //初始化大顶堆（从最后一个非叶节点开始，从左到右，由下到上）
        //构建一个堆
        for (int i = len / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, len);
        }
        System.out.println("dui =" + JSON.toJSONString(arr));
        //将顶节点和最后一个节点互换位置，再将剩下的堆进行调整
        for (int j = len - 1; j > 0; j--) {
            swap(arr, 0, j);
            System.out.println("j=" + j + "，dui1 =" + JSON.toJSONString(arr));
            adjustHeap(arr, 0, j);
            System.out.println("j=" + j + "，dui2 =" + JSON.toJSONString(arr));


        }
    }

    /**
     * 整理树让其变成堆
     *
     * @param arr 待整理的数组
     * @param i   开始的结点
     * @param j   数组的长度
     */
    public static void adjustHeap(int[] arr, int i, int j) {
        int temp = arr[i];//定义一个变量保存开始的结点
        //k就是该结点的左子结点下标
        for (int k = 2 * i + 1; k < j; k = 2 * k + 1) {
            //比较左右两个子结点的大小，k始终记录两者中较大值的下标
            if (k + 1 < j && arr[k] < arr[k + 1]) {
                k++;
            }
            //经子结点中的较大值和当前的结点比较，比较结果的较大值放在当前结点位置
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {//说明已经是大顶堆
                break;
            }
        }
        arr[i] = temp;
    }

    /**
     * 交换数据
     *
     * @param arr
     * @param num1
     * @param num2
     */
    public static void swap(int[] arr, int num1, int num2) {
        int temp = arr[num1];
        arr[num1] = arr[num2];
        arr[num2] = temp;
    }
}
