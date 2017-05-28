package bit.work.shop.utils;

public class HotNode implements Comparable<HotNode>{
	
	
	private int mid;
	private double score;

	public HotNode(int mid, double score){
		this.mid=mid;
		this.score=score;
	}
	public HotNode(){
		
	}
	
	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public int compareTo(HotNode node) {
		double temp=score-node.getScore();
		if(temp>0){
			return 1;
		}else if(temp==0){
			return 0;
		}else{
			return -1;
		}
	}

}
