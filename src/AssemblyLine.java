import java.util.ArrayList;
import java.util.List;



public class AssemblyLine {
		private static final int NUM_OF_ORANGES = 1000;
		
		
		public AssemblyLine(){
			List<String> Oranges = ArrayList <String>();
			for (int i = 0; i < NUM_OF_ORANGES; i++ ){
				Oranges.add("Orange" +i);
			}
		}
		
		public synchronized void getOranges(Oranges o){
			
		}
}
