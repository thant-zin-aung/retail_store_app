package com.blacksky.ko_yan_nyein_aung_retail_store.controller;

import com.blacksky.ko_yan_nyein_aung_retail_store.model.Product;
import com.blacksky.ko_yan_nyein_aung_retail_store.model.ProductManipulator;
import com.blacksky.ko_yan_nyein_aung_retail_store.model.SystemManipulator;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.File;

public class MainController {

    @FXML private AnchorPane root;
    @FXML private ComboBox<String> category;
    @FXML private VBox productsWrapper;
    @FXML private TextField searchBar;
    @FXML private Label minStock,totalCategory,totalStock,totalProduct;
    @FXML private ScrollPane scrollPane;

    public void initialize() {

        SystemManipulator.unlockAppResourceFiles();

        ProductManipulator.searchBar = searchBar;
        ProductManipulator.productsWrapper = productsWrapper;
        ProductManipulator.category = category;
        ProductManipulator.minStock = minStock;
        ProductManipulator.totalCategory = totalCategory;
        ProductManipulator.totalStock = totalStock;
        ProductManipulator.totalProduct = totalProduct;
        category.getItems().addAll(ProductManipulator.DEFAULT_CATEGORY_FIRST_ITEM_NAME,ProductManipulator.DEFAULT_CATEGORY_SECOND_ITEM_NAME);
        if ( new File(SystemManipulator.APP_CATEGORY_PATH+"\\"+SystemManipulator.PRODUCT_DETAIL_EXPORT_FILE_NAME).exists() ) {
            SystemManipulator.importProductDetails(productsWrapper,category);
        }
        if ( new File(SystemManipulator.APP_MIN_STOCK_PATH+"\\"+SystemManipulator.MIN_STOCK_VALUE_FILE_NAME).exists() ) {
            SystemManipulator.importMinStockValue();
        }
        SystemManipulator.createDirectory(SystemManipulator.APP_CATEGORY_PATH);
        SystemManipulator.createDirectory(SystemManipulator.APP_MIN_STOCK_PATH);
        productsWrapper.setSpacing(5);

//        category.getSelectionModel().selectFirst();
//        ProductManipulator.updateSelectableProduct(productsWrapper);
        ProductManipulator.updateUI();
        root.setOnMouseClicked(e-> root.requestFocus());

    }

    @FXML
    public void clickOnAddButton() {
        Product newProduct = ProductManipulator.getAddProduct(category);
        if ( newProduct != null ) {
            ProductManipulator.updateUI();
        }

    }

    @FXML
    public void clickOnCategory() {
        String selectedCategory = category.getSelectionModel().getSelectedItem();
        if ( selectedCategory != null ) {
            ProductManipulator.updateProductWrapperUIByCategory(productsWrapper,selectedCategory);
            ProductManipulator.updateSelectableProduct(productsWrapper);
        }
        ProductManipulator.clickedProductDetailWrapper = null;

    }

    @FXML
    public void pressKeyOnSearchField() {
        category.getSelectionModel().selectFirst();
        String selectedProduct = searchBar.getText();
        ProductManipulator.updateProductWrapperUIByName(productsWrapper,selectedProduct);
        ProductManipulator.updateSelectableProduct(productsWrapper);
        ProductManipulator.clickedProductDetailWrapper = null;
    }

    @FXML
    public void clickOnDeleteButton() {
        if ( ProductManipulator.clickedProductDetailWrapper != null ) {
            ProductManipulator.deleteProduct(ProductManipulator.clickedProductDetailWrapper);
        }
    }

    @FXML
    public void clickOnUpdateButton() {
        if ( ProductManipulator.clickedProductDetailWrapper != null ) {
            if ( ProductManipulator.updateProduct() != null ) ProductManipulator.updateUI();
        }
    }

    @FXML
    public void clickOnSetMinButton() {
        ProductManipulator.setMinimumStockValue();
    }
}