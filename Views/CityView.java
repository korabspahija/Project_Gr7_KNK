

import com.sun.javafx.scene.control.skin.ComboBoxListViewSkin;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class CityView extends Application
{
    public static class HideableItem<T>
    {
        private final ObjectProperty<T> object = new SimpleObjectProperty<>();
        private final BooleanProperty hidden = new SimpleBooleanProperty();

        private HideableItem(T object)
        {
            setObject(object);
        }

        private ObjectProperty<T> objectProperty(){return this.object;}
        private T getObject(){return this.objectProperty().get();}
        private void setObject(T object){this.objectProperty().set(object);}

        private BooleanProperty hiddenProperty(){return this.hidden;}
        private boolean isHidden(){return this.hiddenProperty().get();}
        private void setHidden(boolean hidden){this.hiddenProperty().set(hidden);}

        @Override
        public String toString()
        {
            return getObject() == null ? null : getObject().toString();
        }
    }

  public void start(Stage stage)
    {
        List<String> cities= new ArrayList<>();
        	
        cities.add("Prishtina");      

        ComboBox<HideableItem<String>> comboBox = createComboBoxWithAutoCompletionSupport(cities);
        comboBox.setMaxWidth(Double.MAX_VALUE);
       
        HBox hBoxRemoveCity = new HBox();
        
        HBox hBoxAddCity = new HBox();
        
        Button btnRemoveCity = new Button("Fshij qytetin");
        
        VBox MainVBox = new VBox(30);
        
        TextField tfAddCity = new TextField();
        
        
        Button btnAddCity = new Button("Shto qytetin");
       
        
        
        //mock table kur krijohet lidhet me databaze krijohet e verteta 
        TableView table = new TableView();
        table.setEditable(true);
        
        TableColumn firstNameCol = new TableColumn("CityID");
        TableColumn lastNameCol = new TableColumn("City Name");
        
        table.getColumns().addAll(firstNameCol, lastNameCol);
        
        table.setEditable(true);
        firstNameCol.setMinWidth(265);
        lastNameCol.setMinWidth(265);
        
        
        
        hBoxRemoveCity.getChildren().addAll(comboBox,btnRemoveCity);
        
        hBoxAddCity.getChildren().addAll(tfAddCity,btnAddCity);
        
        
        MainVBox.getChildren().addAll(hBoxAddCity,hBoxRemoveCity,table);
        
        
        Scene scene = new Scene(MainVBox,530,500);
        stage.setScene(scene);
        stage.show();

        comboBox.setMinWidth(200);
        comboBox.setPrefWidth(comboBox.getWidth());
        
    }

    public static void main(String[] args)
    {
        launch();
    }

    @SuppressWarnings("rawtypes")
	private static <T> ComboBox<HideableItem<T>> createComboBoxWithAutoCompletionSupport(List<T> items)
    {
        ObservableList<HideableItem<T>> hideableHideableItems = FXCollections.observableArrayList(hideableItem -> new Observable[]{hideableItem.hiddenProperty()});

        items.forEach(item ->
        {
            HideableItem<T> hideableItem = new HideableItem<>(item);
            hideableHideableItems.add(hideableItem);
        });

        FilteredList<HideableItem<T>> filteredHideableItems = new FilteredList<>(hideableHideableItems, t -> !t.isHidden());

        ComboBox<HideableItem<T>> comboBox = new ComboBox<>();
        comboBox.setItems(filteredHideableItems);

        @SuppressWarnings("unchecked")
        HideableItem<T>[] selectedItem = (HideableItem<T>[]) new HideableItem[1];

        comboBox.addEventHandler(KeyEvent.KEY_PRESSED, event ->
        {
            if(!comboBox.isShowing()) return;

            comboBox.setEditable(true);
            comboBox.getEditor().clear();
        });

        comboBox.showingProperty().addListener((observable, oldValue, newValue) ->
        {
            if(newValue)
            {
                @SuppressWarnings({ "unchecked" })
                ListView<HideableItem> lv = ((ComboBoxListViewSkin<HideableItem>) comboBox.getSkin()).getListView();

                Platform.runLater(() ->
                {
                    if(selectedItem[0] == null) // first use
                    {
                        double cellHeight = ((Control) lv.lookup(".list-cell")).getHeight();
                        lv.setFixedCellSize(cellHeight);
                    }
                });

                lv.scrollTo(comboBox.getValue());
            }
            else
            {
                HideableItem<T> value = comboBox.getValue();
                if(value != null) selectedItem[0] = value;

                comboBox.setEditable(false);

                Platform.runLater(() ->
                {
                    comboBox.getSelectionModel().select(selectedItem[0]);
                    comboBox.setValue(selectedItem[0]);
                });
            }
        });

        comboBox.setOnHidden(event -> hideableHideableItems.forEach(item -> item.setHidden(false)));

        comboBox.getEditor().textProperty().addListener((obs, oldValue, newValue) ->
        {
            if(!comboBox.isShowing()) return;

            Platform.runLater(() ->
            {
                if(comboBox.getSelectionModel().getSelectedItem() == null)
                {
                    hideableHideableItems.forEach(item -> item.setHidden(!item.getObject().toString().toLowerCase().contains(newValue.toLowerCase())));
                }
                else
                {
                    boolean validText = false;

                    for(HideableItem hideableItem : hideableHideableItems)
                    {
                        if(hideableItem.getObject().toString().equals(newValue))
                        {
                            validText = true;
                            break;
                        }
                    }

                    if(!validText) comboBox.getSelectionModel().select(null);
                }
            });
        });

        return comboBox;
    }
}