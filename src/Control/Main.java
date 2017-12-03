package Control;

import Model.Ring_Contain;
import View.Ring_view;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	static Ring_view view;
	static Ring_Contain<String> model;
	
	@Override
	public void start(Stage primaryStage) {
		view = new Ring_view();
		model = new Ring_Contain<String>();
		primaryStage.setTitle("Ring Form");
		primaryStage.setScene(view.getScene());
		primaryStage.setHeight(625);
		primaryStage.setWidth(915);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main(String[] args) {

		
		launch(args);
		
	}
	
	/**
	 * event add new element on the field and in the ring
	 * @param str data for add in ring
	 */
	public void AddElement(String str, String type)
	{
		if (CheckTypeValue(str, type))
		{
			if (model.isUnique(str))
			{
				view.AddNewCircle(str);
				model.AddInEnd(str);
			}
			else
				view.setError("Dublicate data, element with contents of: "+ str +" can't added");
		}
		else
			view.setError("Invalid type, element with contents of: "+ str +" can't added");
	}
	
	/**
	 * event delete element from the field and from the ring
	 * @param str data for add in ring
	 */
	public void DeleteElement(String str)
	{
		if(model.FindElement(str))
		{
			view.DeleteCircle(str);
			model.DeleteElement(str);
		}
		else
			view.setError("Element with contents of "+ str +" not found");
	}
	
	public boolean CheckTypeValue(String str, String type)
	{
		if (!str.equals(""))
			switch (type) {
			case "Integer":{
				try {
					Integer.parseInt(str);
					return true;
				}
				catch (NumberFormatException e) {
					return false;
				}
			}
			
			case "Double":{
				try {
					Double.parseDouble(str);
					return true;
				}
				catch (NumberFormatException e) {
					return false;
				}
			}
			
			case "Boolean":{
				try {
					if (str.equals("true") || str.equals("false"))
						return true;
					else
						return false;
				}
				catch (NumberFormatException e) {
					return false;
				}
			}
			}
		else
			return false;
		return true;
	}
	
	public void ChangeElem(String str, String newstr, String type) {
		if (CheckTypeValue(newstr, type))
		{
			if (model.isUnique(newstr) && !newstr.equals(str))
			{
				view.ChangeElem(str, newstr);
				model.ChangeElem(str, newstr);
			}
			else
				view.setError("Invalid data, element with contents of: "+ str +" can't change to "+ newstr);
		}
		else
			view.setError("Invalid type, element with contents of: "+ str +" can't change to "+ newstr);
	}
}
