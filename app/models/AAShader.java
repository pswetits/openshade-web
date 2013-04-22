package models;


import java.io.*;
import java.util.ArrayList;
import matrices.Blosum62;


public class AAShader {
	


	/**
	 * instance data, vector of AminoAcidSequences 
	 */
	ArrayList<AminoAcidSequence> sequences;
	int seqNum;
	int consensusPercent;
	int consensusCounter = 0;
	int similarityCounter = 0;
	
	/**
	 * constructor sets sequences and calls shade()
	 */
	public AAShader(ArrayList<AminoAcidSequence>sequences, int seqNum) throws IOException{
		this.sequences = sequences;
		this.seqNum = seqNum;

		if (seqNum % 2 != 0)
			consensusPercent = seqNum / 2 + 1;
		else consensusPercent = seqNum / 2;

		shadeIdentities();
		shadeSimilarities();
	}
	
	public void setDefaultShading() {
		int x = 0;
		while(x < sequences.get(0).getLength()){
			for(int i = 0; i < seqNum; i++)
				sequences.get(i).getAAType(x).setShading(0);
		x++;
		}
	}
	
	/**
	 * method responsible for overseeing shading 
	 */
	public void shadeIdentities(){
		
		int x = 0;
		while(x < sequences.get(0).getLength()){ /** increment through each AA position */
			boolean identical = true;
			for (int j = 1; j < seqNum; j++)
				if (!sequences.get(j-1).getAA(x).equals(sequences.get(j-1).getAA(x)))
					identical = false;

			if (identical) {
				for(int i = 0; i < seqNum; i++) 
					sequences.get(i).getAAType(x).setShading(1);	
			}
			x++;
		}
	}
	
	
		
	/**
	 * shading method for columns that contain all of the same Amino Acids
	 */

	
	
	public void shadeSimilarities(){
		
		/** if all identities are conserved, shade entire column */
		int y = 0;
		while(y < sequences.get(0).getLength()){
			String c = getConsensus(y);
				
			if(c.indexOf(',') == -1 && consensusCounter > 2)
				colorSimilarities(c, y);
			/*else if(c.indexOf(',') == -1 && consensusCounter > 1 && similarityCounter > 0)
				shadeSimilaritiesMultiple(c, y);
			else if(c.indexOf(',') > -1 && consensusCounter > 1){
				String[] multipleConsensus = c.split(",");
				for(String s : multipleConsensus)
					shadeSimilaritiesMultiple(s, y);
			}*/
			y++;

		}
		//OpenGUI.refresh();
	}
		
	/**
	 * shading method for semi-conserved columns
	 */
	public void colorSimilarities(String c, int x){
		if(!c.equals("null")){
			for(int i = 0; i < 5; i++){
				/** chars of current AA and consensus AA */
				String current = sequences.get(i).getAA(x);
				String consensus = c; 
			
				/** shade black if current AA equals consensus */
				if(current.equals(consensus))
					sequences.get(i).getAAType(x).setShading(1);
			
				/** shade grey if score of current and consensus is 0 or above */
				else if(Blosum62.getScore(consensus, current) > 0)
					sequences.get(i).getAAType(x).setShading(2);
	
			}
		}

	}
	
	/*
	 shading method for semi-conserved columns with two consensus 
	 
	public void shadeOtherSimilarities(){
		int x = 0;
		while(x < sequences.get(0).getLength()){
			String c = getConsensus(x);
			if(!c.equals("-")){
				for(int i = 0; i < 5; i++){
					/** chars of current AA and consensus AA 
					String current = sequences.get(i).getAA(x);
					String consensus = c; 
			
					/** shade black if current AA equals consensus */
					//if(current == consensus)
						//sequences.get(i).getAAType(x).setShading(2);
			
					/** shade grey if score of current and consensus is 0 or above 
					if(Blosum62.getScore(consensus, current) > 0 && sequences.get(i).getAAType(x).getShading() == 0)
						sequences.get(i).getAAType(x).setShading(3);
	
				}
			}
			x++;
		} 
		//OpenGUI.refresh();		

	}
	
	
	 * get AA consensus throughout all proteins at given position x 
	 */
	public String getConsensus(int x){
		consensusCounter = 0;
		similarityCounter = 0;
		int tempCounter = 0;
		String consensus = "shade";
		String tempAA = "";
		
		/** increment through each AA */
		for(int z = 0; z < seqNum; z++){
			tempAA = sequences.get(z).getAA(x);
			tempCounter = 0;
			
			/** compare tempAA to other AA in column */
			for(int i = 0; i < seqNum; i++){
				if(tempAA.equals(sequences.get(i).getAA(x)))
					tempCounter++;	/** increment tempCounter if match */
			}
			/** sets new temporary consensus if greater than previous */
			if(tempCounter > consensusCounter && !tempAA.equals("-")){
				consensusCounter = tempCounter;
				consensus = tempAA;
			}
			if(tempCounter == consensusCounter && !tempAA.equals(consensus) && !tempAA.equals("-")){
				consensus = consensus + "," + tempAA;
			}
			if(!tempAA.equals("-") && !tempAA.equals(consensus) && Blosum62.getScore(tempAA, consensus) > 0)
				similarityCounter++;
				
		}
		
		return consensus; /** String "shade" returned if no consensus set */
	}
	
}