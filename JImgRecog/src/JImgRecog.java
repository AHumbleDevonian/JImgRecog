
public class JImgRecog {
	
	public JImgRecog(){
		//To Do
	}	
		
	public void XORTest(){
	    double[][] data = new double[][] {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
	    double[] expected = new double[] {0, 1, 1, 0};
	    
	    double momentum = 0.8;
	    double learnRate = 0.5;
	    int epochs = 750;
		int[] inputs = new int[]{2};
	    int[] secondLayerInputs = new int[]{2,2};
	    JImgRecogLayer outerLayer = new JImgRecogLayer(1,inputs,momentum,learnRate,null);
	    JImgRecogLayer upperLayer = new JImgRecogLayer(2, secondLayerInputs,momentum,learnRate,outerLayer);	
	    		

	    for (int j = 0; j < epochs; j++) {
	        for (int i = 0; i < 4; i++) {
	            upperLayer.TrainLayer(data[i], expected[i]);
	            upperLayer.ReassignLayerWeights(data[i]);
	        }
	    }
	
	    for(int i = 0; i < 4; i++){
	    	System.out.print(" Inputs: ");
	    	System.out.print(data[i][0]);
	    	System.out.print(data[i][1]);
	    	System.out.print("  Outputs:  ");	
	    	double[] out = upperLayer.GetOutputs(data[i]);   		    	
	    	System.out.print(out[0]);
	    }
	}
	

}
