package View;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import Control.Main;
import Control.control;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javax.swing.JOptionPane;

public class Ring_view {
	Main control;
	String mode;
	Scene scen;
	Group group;
	AnchorPane Panel_Ring;
	GridPane Panel_Ctrl;
	HBox horisontal_global;
	Button btn_add, btn_del, btn_find, btn_chg;
	TextField text_data;
	Label help, lab_choice_box;
	ArrayList<Button> list;
	ComboBox<String> valueType;
	
	public Ring_view(){
		control = new Main();
		group = new Group();
		list = new ArrayList<Button>();
		mode = "add";
		
		setPanelView(570, 570);
		
		setTextData();
		setLabelHelp();
		setButtonAdd();
		setButtonDelete();
		setButtonFind();
		setButtonChange();
		setChoiceBoxType();
		
		setPanelCtrl(300,570);
		
		horisontal_global = new HBox();
		horisontal_global.getChildren().addAll(Panel_Ring,Panel_Ctrl);
		horisontal_global.setSpacing(15);
		horisontal_global.setPadding(new Insets(15,0,0,15));
		
		group.getChildren().add(horisontal_global);
		
		HBox b = (HBox) group.getChildren().get(0);
		b.getChildren().get(0);
	}
	
	/**
	 * Return scene for class Main
	 * @return
	 */
	public Scene getScene() {
		scen = new Scene(group);
		scen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		return scen;
	}
	
	/**
	 * Increase amount of elements on field by 1 with content
	 * @param str content new element
	 */
	public void AddNewCircle(String str) {
		list.add(getButton(0, 0, str));
		setCircleAroundPoint(250, 250, 200);
		Panel_Ring.getChildren().add(list.get(list.size()-1));
	}
	
	/**
	 * Increase amount of elements on field by 1 with content
	 * @param str content new element
	 */
	public void DeleteCircle(String str) {
		for (int i = 0; i < list.size();i++) {
            if (list.get(i).getText() != null && list.get(i).getText().equals(str)) {
            	Panel_Ring.getChildren().remove(list.get(i));
            	list.remove(i);
                break;
            }
        }
		setCircleAroundPoint(250, 250, 200);
	}
	
	public void ChangeElem(String str, String newstr) {
		for (int i = 0; i < list.size();i++) {
            if (list.get(i).getText() != null && list.get(i).getText().equals(str)) {
            	list.get(i).setText(newstr);
            	
                break;
            }
        }
	}

	public void setError(String str) {
		help.setText(str);
		help.setStyle("-fx-text-fill: red");
	}

	/**
	 * Set text field settings of new element content and event press enter
	 */
	private void setTextData() {
		text_data = new TextField();
		text_data.setPrefWidth(250);
		text_data.setOnKeyPressed(new EventHandler<KeyEvent>()
	    {
	        @Override
	        public void handle(KeyEvent ke)
	        {
	            if (ke.getCode().equals(KeyCode.ENTER))
	            {
	            	switch(mode) {
	            	case "add":{
	            		help.setStyle("");
	            		help.setText("Input data for additing in the ring and press Enter");
	            		control.AddElement(text_data.getText(), valueType.getValue());
	            		text_data.setText("");
	            		break;
	            	}
	            	case "del":{
	            		help.setStyle("");
	            		help.setText("Pick element witch you want to delete or input its content");
	            		control.DeleteElement(text_data.getText());
	            		text_data.setText("");
	            		break;
	            	}
	            	case "find":{
	            		control.DeleteElement(text_data.getText());
	            		break;
	            	}
	            	default:{
	            		break;
	            	}
	            		
	            	}
	            }
	        }
	    });
		
	}
	
	/**
	 * Set text field settings of new element content and event press enter
	 */
	private void setLabelHelp() {
		help = new Label("Input data for additing in the ring and press Enter");
		help.setPrefWidth(250);
		help.setWrapText(true);
		help.setTextAlignment(TextAlignment.CENTER);
		help.setPrefHeight(45);
	}
	
	/**
	 * Set button settings of addition new element and set event press button
	 */
	private void setButtonAdd() {
		btn_add = new Button("Add");
		btn_add.setStyle("-fx-background-color: #00ff00");
		btn_add.setPrefSize(120, 20);
		btn_add.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				resetColorCtrlButtons();
				btn_add.setStyle("-fx-background-color: #00ff00");
				help.setText("Input data for additing in the ring and press 'Добавить'");
				help.setStyle("");
				mode = "add";
				control.AddElement(text_data.getText(),valueType.getValue());
				text_data.setText("");
				text_data.requestFocus();
			}
		});
	}
	
	/**
	 * Set button settings of delete element and set event press button
	 */
	private void setButtonDelete() {
		btn_del = new Button("Delete");
		btn_del.setPrefSize(120, 20);
		btn_del.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				resetColorCtrlButtons();
				btn_del.setStyle("-fx-background-color: #00ff00");
				help.setText("Pick element witch you want to delete or input its content");
				help.setStyle("");
				mode = "del";
				control.DeleteElement(text_data.getText());
				text_data.requestFocus();
			}
		});
	}
	
	 /** 
	 */
	private void setButtonFind() {
		btn_find = new Button("Find");
		btn_find.setPrefSize(120, 20);
		btn_find.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				if (searchElem(text_data.getText()) != -1)
				{
					changeColorButtonView(text_data.getText());
				}
				else
				{
					setError("Element with contents of "+ text_data.getText() +" not found");
					text_data.requestFocus();
				}
			}
		});
	}
	 
	 /** 
	 */
	private void setButtonChange() {
		btn_chg = new Button("Change");
		btn_chg.setPrefSize(120, 20);
		btn_chg.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				String newdata = "";
				resetColorCtrlButtons();
				help.setText("Pick element witch you want to change or input its content");
				help.setStyle("");
				if (searchElem(text_data.getText()) != -1)
				{
					try {
						
						newdata = JOptionPane.showInputDialog("Введите новое значение");
					}
					catch(NullPointerException f)
					{
					
					}
					control.ChangeElem(text_data.getText(), newdata, valueType.getValue());
					text_data.setText("");
				}
				else
				{
					setError("Element with contents of "+ text_data.getText() +" not found");
					text_data.requestFocus();
				}
			}
		});
	}
	
	private void setChoiceBoxType() {
		lab_choice_box = new Label("Type of elements ring. Be careful if change type, items will delete");
		lab_choice_box.setWrapText(true);
		lab_choice_box.setTextAlignment(TextAlignment.CENTER);
		valueType = new ComboBox<String>();
		valueType.setItems(FXCollections.observableArrayList("Integer","Double", "String", "Boolean"));
		valueType.setPromptText("String");
		valueType.setValue("String");
		valueType.setPrefWidth(180);
		
		valueType.valueProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue ov, String t, String t1) {  
            	int count = list.size();
            	for(int i = count-1; i>=0; i--)
            	{
            		control.DeleteElement(list.get(i).getText());
            	}
            }    
        });
	}
	
	/**
	 * Set view panel settings with contents of ring
	 * @param width 
	 * @param height
	 */
	private void setPanelView(double width, double height) {
		Panel_Ring = new AnchorPane();
		Panel_Ring.setPrefSize(width, height);
		Panel_Ring.getStyleClass().add("panel_view");
	}
	
	private void setPanelCtrl(double width, double height) {
		Panel_Ctrl = new GridPane();
		Panel_Ctrl.setPrefSize(width, height);
		Panel_Ctrl.getColumnConstraints().add(new ColumnConstraints(130)); 
		Panel_Ctrl.getColumnConstraints().add(new ColumnConstraints(130));
		Panel_Ctrl.getStyleClass().add("panel_ctrl");
		Panel_Ctrl.setPadding(new Insets(10, 10, 10, 10) ); 
		Panel_Ctrl.setVgap(15) ; 
		Panel_Ctrl.setHgap(5) ; 
		Panel_Ctrl.setAlignment(Pos.TOP_CENTER) ; 
		Panel_Ctrl.add(text_data, 0, 0, 2, 1);
		Panel_Ctrl.add(help, 0, 1, 2, 1);
		Panel_Ctrl.add(btn_add, 0, 2);
		Panel_Ctrl.add(btn_del, 1, 2);
		Panel_Ctrl.add(btn_find, 0, 3);
		Panel_Ctrl.add(btn_chg, 1, 3);
		Panel_Ctrl.add(lab_choice_box, 0, 4, 2, 1);
		Panel_Ctrl.add(valueType, 0, 5, 2, 1);
		//Panel_Ctrl.setGridLinesVisible(true);
		GridPane.setHalignment(help, HPos.CENTER);
		GridPane.setHalignment(text_data, HPos.CENTER);
		GridPane.setHalignment(btn_add, HPos.CENTER);
		GridPane.setHalignment(btn_del, HPos.CENTER);
		GridPane.setHalignment(btn_find, HPos.CENTER);
		GridPane.setHalignment(btn_chg, HPos.CENTER);
		GridPane.setHalignment(valueType, HPos.CENTER);
		GridPane.setHalignment(lab_choice_box, HPos.CENTER);
	}

	/**
	 * Arrange all buttons around point with radius
	 * @param x coordinate width
	 * @param y coordinate height
	 * @param r radius circle
	 */
	private void setCircleAroundPoint(double x, double y, double r) {
		double d,h,v;
		double count = list.size();
		for (int i = 0; i < count; i++) {
			d = 2/count*(double)i*Math.PI;
			h = y + r*Math.sin(d);
			v = x + (-1)*r*Math.cos(d);
			list.get(i).setTranslateX(h);
			list.get(i).setTranslateY(v);
		}
	}
	
	/**
	 * return button for view field
	 * @param x coordinate width
	 * @param y coordinate height
	 * @param str text button
	 * @return button for field
	 */
	private Button getButton(double x, double y, String str) {
		Button btn = new Button(str);
		btn.setPrefSize(70, 70);
		btn.setTranslateX(x);
		btn.setTranslateY(y);
		btn.getStyleClass().add("button_circle");
		
		btn.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				switch(mode) {
            	case "del":{
            		control.DeleteElement(btn.getText());
            		break;
            	}
            	case "change":{
            		//control.DeleteElement(text_data.getText());
            		break;
            	}
            	default:{
            		break;
            	}
            		
            	}
			}
		});
		
		btn.setOnMouseMoved(new javafx.event.EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				double d = btn.getText().length()*10+20;
				if (d < 70)
					d = 70;
				btn.setPrefSize(d, 70);
			}
		});
		
		btn.setOnMouseExited(new javafx.event.EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				btn.setPrefSize(70, 70);
			}
		});
		return btn;
	}
	
	private void resetColorCtrlButtons() {
		btn_del.setStyle("");
		btn_add.setStyle("");
		btn_find.setStyle("");
		btn_chg.setStyle("");
	}
	
	private int searchElem(String str) {
		for (int i = 0; i < list.size();i++) {
            if (list.get(i).getText() != null && list.get(i).getText().equals(str)) {
            	return i;
            }
		}
		return -1;
	}
	
	private void changeColorButtonView(String str) {
		Timer d = new Timer();
		
		TimerTask r = new TimerTask() {
			
			@Override
			public void run() {
				list.get(searchElem(str)).setStyle("-fx-background-color: green");
			}
		};
		list.get(searchElem(str)).setStyle("-fx-background-color: #00ff00");
		d.schedule(r,(long)500);
	}
		
		
	
	
	

}
