package bit.work.shop.utils;

import java.util.ArrayList;

/**
 * 
 * @author Administrator
 * 堆/ 二叉堆-- - 一种结构
 * 表现特点:
 * 1.父元素总是大于(小于)等于他的两个子元素
 * 2.是一个完美二叉树,或接近完美二叉树
 * 
 * 左节点: 2 * i + 1
 * 右节点: 2 * i +2
 * 父节点: 下取整 ( i-1 )/2
 * 
 * 添加新的节点:  将新节点添加到list末尾, 在向上过滤              --- 时间复杂度 logN
 * 移除首节点     :	  用数组末尾的元素顶替到头部, 再向下过滤      --- 时间复杂度  logN
 * 
 * 向上过滤: filterUp(index)  --- logN
 * 		只要比父元素小(大)就和父元素交换
 * 向下过滤: filterDown(index)  --- logN
 * 		不停的和左右子元素中最小的元素交换
 * 
 * 堆排序: 
 * 		最好--- NlogN
 * 		最坏--- 2NlogN
 * 		平均--- 2NlogN
 * 
 * 
 */
public class Heap<E extends Comparable<E>> {
	private ArrayList<E> data=null;
	
	public Heap(){
		data=new ArrayList<E>();
	}
	
	// 数组堆化的时间复杂度  NlogN
	public Heap(ArrayList<E> unsortedData){
		data=new ArrayList<E>();
		for(E item:unsortedData){
			data.add(item);
		}
		// 注意堆化的起点位置
		for (int i = ( data.size() /2 ) -1; i >= 0; i--) {
			filterDown(i);
		}
		
	}
	
	public static <E extends Comparable<E>> void heapSort(ArrayList<E> data){
		Heap<E> heap=new Heap<E>(data);
		for (int i = 0; i < data.size(); i++) {
			data.set(i, heap.remove());
		}
	}
	
	public void add(E item){
		data.add(item);
		filterUp(data.size()-1);
	}
	
	// 边界, 被操作的list长度为1
	public E remove(){ 
		E result=data.get(0);
		E lastElement=data.remove(data.size()-1);
		if(data.size()>0){
			data.set(0, lastElement);
			filterDown(0);
		}
		return result;
	}
	
	private void filterUp(int index) {
		int parent=parentIndex(index);
		while(parent>=0){
			if(data.get(index).compareTo(data.get(parent)) < 0){
				swap(parent,index);
				index=parent;
				parent=parentIndex(index);
			}else{
				return;
			}
		}
		
	}
	
	private void swap(int parent, int index) {
		E temp = data.get(parent);
		data.set(parent, data.get(index));
		data.set(index, temp);
		
		
	}
	// 边界为index==data.length, index==smallest
	private void filterDown(int index) {
		while(index<data.size()){
			int left=leftChidIndex(index);
			int right=rightChildIndex(index);
			int smallest=index;
			if(left<data.size() && data.get(left).compareTo(data.get(smallest)) <0 ){
				smallest=left;
			}
			if(right< data.size() && data.get(right).compareTo(data.get(smallest) )<0){
				smallest=right;
			}
			if(smallest==index){
				return;
			}
			swap(index,smallest);
			index=smallest;
		}
		
	}
	private int parentIndex(int index){
		return (index-1)/2;
	}
	private int leftChidIndex(int index){
		return 2*index+1;
	}
	private int rightChildIndex(int index){
		return 2*index + 2 ;
	}
	public boolean isEmpty(){
		return data.isEmpty();
	}

}
