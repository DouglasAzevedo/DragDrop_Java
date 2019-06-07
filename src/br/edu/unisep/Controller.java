package br.edu.unisep;

import br.edu.unisep.fx.controller.AppController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class Controller extends AppController {

    @FXML private ListView<String> lstOrigem;
    @FXML private ListView<String> lstDestino;

    private ObservableList<String> itensOrigem;
    private ObservableList<String> itensDestino;

    @Override
    protected void onInit() {
        itensOrigem = FXCollections.observableArrayList();
        lstOrigem.setItems(itensOrigem);
        itensDestino = FXCollections.observableArrayList();
        lstDestino.setItems(itensDestino);

        carregarItensOrigem();
    }

    public void carregarItensOrigem(){
        for(int i =0; i< 10; i++){
            itensOrigem.add("Exemplo Item " + i);
        }
    }

    public void onDragDetected(MouseEvent event){
        //Inicia o projeto de drag and drop
        var dragboard = lstOrigem.startDragAndDrop(TransferMode.MOVE);
        //Obtem a posição selecionada na lista de origem para arrastar;
        var content = new ClipboardContent();
        //Cria Objeto que representa o conteúdo que será arrastado para a lista de destino
        var pos = lstOrigem.getSelectionModel().getSelectedIndex();
        //Adiciona a posição do item selecionado na origem pra ser enviado no dragboard
        //Para posteriormente ser recuperado ao soltar o item na lista de destino;
        content.putString(String.valueOf(pos));
        //Adiciona o conteúdo no dragboard, para ser enviado;
        dragboard.setContent(content);
        //Indica que o evento de drag não será mais detectado por outros componentes;
        event.consume();

    }

    public void dragOver(DragEvent event){
        //Indica que a lista de destino aceita a conclusão de evento de dragDrop
        event.acceptTransferModes(TransferMode.MOVE);
        event.consume();
    }

    public void drop(DragEvent event){
        //Recupera o dragboard que foi enviado na origem do movimento
        var dragBoard = event.getDragboard();
        //Recupera a informação da posição selecionada na origem
        var pos = dragBoard.getString();
        //Obtém o item da lista de origem
        var item = itensOrigem.get(Integer.parseInt(pos));
        //Remove o item da origem;
        itensDestino.add(item);
        itensOrigem.remove(item);
        event.consume();
    }


}
