package thexnator.furnituremod.util.utilobjects;

public class SlowdownUtil{
	
	public int lenght,progress=0;

	public SlowdownUtil(int lenght){
		this.lenght=lenght;
	}
	
	public boolean isTimeWithAddProgress(){
		addProgress();
		return isTime();
	}
	
	public boolean isTime(){
		boolean result;
		result=lenght<=progress?true:false;
		if(result)RessetProgress();
		return result;
	}
	
	public void addProgress(){
		progress++;
	}
	
	public void RessetProgress(){
		progress=0;
	}
	
	public void ChangeLenght(int a){
		lenght=a;
	}
	
}
