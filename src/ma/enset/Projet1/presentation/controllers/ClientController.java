package ma.enset.tp_jdbc.presentation.controllers;

import javafx.fxml.Initializable;

import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.tp_jdbc.services.*;

public class ClientController implements Initializable {
    @FXML
    private Button Ajouter;

    @FXML
    private Button Modifier;

    @FXML
    private Button Supprimer;

    @FXML
    private TableView<Client> clientTableView;
    @FXML
    private TableColumn<Client, String> colCin;

    @FXML
    private TableColumn<Client, Date> colDateN;

    @FXML
    private TableColumn<Client, String> colEmail;

    @FXML
    private TableColumn<Client,Integer> colID;

    @FXML
    private TableColumn<Client, String> colNom;

    @FXML
    private TableColumn<Client, String> colPrenom;

    @FXML
    private TableColumn<Client, String> colTele;



    @FXML
    private TextField textCin;

    @FXML
    private TextField textDateNaissance;

    @FXML
    private TextField textEmail;

    @FXML
    private TextField textNom;

    @FXML
    private TextField textPrenom;

    @FXML
    private TextField textSearch;

    @FXML
    private TextField textTele;


    ObservableList<Client> clientObservableList = FXCollections.observableArrayList();
    private ClientService clientService = new ClientServiceImp(new ClientDaoImp());


    public void initialize(URL location, ResourceBundle resources) {
        clientTableView.setItems(clientObservableList);
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        colTele.setCellValueFactory(new PropertyValueFactory<>("tele"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDateN.setCellValueFactory(new PropertyValueFactory<>("date_naiss"));



        loadClients();
        textSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                clientObservableList.clear();
                clientObservableList.addAll(clientService.getClientParMC(newValue));
            }
        });
        clientTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Client>() {
            @Override
            public void changed(ObservableValue<? extends Client> observable, Client oldValue, Client newValue) {
                editClient(newValue);
            }
        });

    }
  public void editClient(Client c)
    {
        textNom.setText(c.getNom());
        textPrenom.setText(c.getPrenom());
        textCin.setText(c.getCin());
        textTele.setText(c.getTele());
        textEmail.setText(c.getEmail());
        textDateNaissance.setText(String.valueOf(c.getDate_naiss()));

    }
    public void addClient() throws ParseException {
        Client c = new Client();
        c.setNom(textNom.getText());
        c.setPrenom(textPrenom.getText());
        c.setCin(textCin.getText());
        c.setTele(textTele.getText());
        c.setEmail(textEmail.getText());
        c.setDate_naiss(textDateNaissance.getText());


        clientService.addClient(c);
        loadClients();

    }/*
    public void deleteProduit()
    {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> optional = alert.showAndWait();
        if(optional.get()==ButtonType.OK)
        {
            Produit p=  produitTableView.getSelectionModel().getSelectedItem();
            produitService.deleteProduit(p);
            loadProduits();
        }

    }*/
    private void loadClients()
    {
        clientObservableList.clear();
        clientObservableList.addAll(clientService.getAllClients());

    }/*
    public void update()
    {
        Produit p= produitTableView.getSelectionModel().getSelectedItem();
        p.setNom(textNom.getText());
        p.setDescription(textDescription.getText());
        p.setPrix(Double.parseDouble(textPrix.getText()));
        p.setQuantite(Integer.parseInt(textQuantite.getText()));
        produitService.UpdateProduit(p);
        loadProduits();

    }*/
    public void searchByKeyWord()
    {

    }
}


