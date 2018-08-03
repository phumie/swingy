package controller;

import java.util.*;

// import assets.*;
import view.*;
import filewriter.*;
import filewriter.WriteFile;
import utilities.Global;
// import model.*;

public class Main {
	public static void main (String[] args){
		PlayersGenerator playersGenerator;
		String line = null;
		int mode = 0;

		try{
			WriteFile.createFile();

//			switch (args[0]) {
//                case "console":
//					PrintMenu.printMode();
//					Scanner scanner = new Scanner(System.in);
//					while (scanner.hasNextLine()){
//						line = scanner.nextLine();
//
//						if (line.matches("\\s*[1-2]\\s*")){
//							mode = Integer.parseInt(line);
//
//							if (mode == 1){
//								CLView.begin();
//								break;
//							}
//							else if (mode == 2){
//								GUIView guiView = new GUIView();
//								guiView.welcomeFrame();
//								break;
//							}
//						}
//						else
//							System.out.println("Invalid mode selection. Try again.");
//					}
//                    break;
//                case "gui":
//					GUIView guiView = new GUIView();
//					guiView.welcomeFrame();
//					break;
//                default:
//					System.out.println("Invalid mode argument. Try again.");
//                    break;
//            }

			
			 PrintMenu.printMode();
			 Scanner scanner = new Scanner(System.in);

			 while (scanner.hasNextLine()){
			 	line = scanner.nextLine();

			 	if (line.matches("\\s*[1-2]\\s*")){
			 		mode = Integer.parseInt(line);
					
			 		if (mode == 1){
			 			// System.out.println("selection: " + mode);
			 			CLView.begin();
			 			break;
			 		}
			 		else if (mode == 2){
			 			// System.out.println("selection: " + mode);
			 			GUIView guiView = new GUIView();
			 			guiView.welcomeFrame();
			 			break;
			 		}
			 	}
			 	else
			 		System.out.println("Invalid mode selection. Try again.");
			 }
		}
		catch (Exception e){
			System.out.println("Game mode selection error: " + e);
		}
		finally{
			WriteFile.closeFile();
			// WriteFile.closeFile();
		}
	}
}