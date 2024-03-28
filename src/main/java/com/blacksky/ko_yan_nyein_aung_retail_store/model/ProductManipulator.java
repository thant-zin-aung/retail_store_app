package com.blacksky.ko_yan_nyein_aung_retail_store.model;

import com.blacksky.ko_yan_nyein_aung_retail_store.Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ProductManipulator {

    public static final String DEFAULT_CATEGORY_FIRST_ITEM_NAME = "All products";
    public static final String DEFAULT_CATEGORY_SECOND_ITEM_NAME = "Low stocks";
    public static int MIN_STOCK_VALUE = 0;
    public static int MAX_PROD_PER_CAT_VALUE = 0;
    public static VBox productsWrapper = null;
    public static ComboBox<String> category = null;
    public static TextField searchBar;
    public static Label minStock,totalCategory,totalStock,totalProduct;
    public static HBox clickedProductDetailWrapper = null;
    public static final int PRODUCT_NAME =  0;
    public static final int PRODUCT_CATEGORY =  1;
    public static final int PRODUCT_DATE =  2;
    public static final int PRODUCT_TIME = 3;
    public static final int PRODUCT_RETAIL_PRICE =  4;
    public static final int PRODUCT_WHOLESALE_PRICE =  5;
    public static final int PRODUCT_QUANTITY =  6;

    private static void setFocus (boolean set,Node node) {
        node.setFocusTraversable(set);
    }

    private static boolean isMatch(String value1,String value2) {
        return value1.equalsIgnoreCase(value2);
    }

    public static HBox getProductDetailWrapper(Product product) {
        DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
        HBox productDetailWrapper = new HBox();
        VBox productNameWrapper = new VBox(new Label(product.getName()));
        VBox categoryWrapper = new VBox(new Label(product.getCategory()));
        VBox productDateWrapper = new VBox(new Label(product.getStringDate()));
        productDateWrapper.setPrefHeight(10);
        VBox productTimeWrapper = new VBox(new Label(product.getStringTime()));
        productNameWrapper.setPrefHeight(10);
        VBox productDateAndTimeWrapper = new VBox(productDateWrapper,productTimeWrapper);
        VBox productRetailPriceWrapper = new VBox(new Label(product.getRetailPrice()));
        VBox productWholesaleWrapper = new VBox(new Label(product.getWholesalePrice()));
        VBox productQuantityWrapper = new VBox(new Label(String.valueOf(product.getProductQuantity())));

        productNameWrapper.setPrefWidth(200);
        productNameWrapper.setAlignment(Pos.CENTER_LEFT);
        productNameWrapper.setPadding(new Insets(0,0,0,10));
        productNameWrapper.getChildren().get(0).setStyle("-fx-font-size: 13px;");
        categoryWrapper.setPrefWidth(160);
        categoryWrapper.setAlignment(Pos.CENTER_LEFT);
        categoryWrapper.getChildren().get(0).getStyleClass().add("productDetailTextStyle");
//        productDateWrapper.setPrefWidth(150);
//        productDateWrapper.setAlignment(Pos.CENTER_LEFT);
//        productDateWrapper.getChildren().get(0).getStyleClass().add("productDetailTextStyle");
        productDateAndTimeWrapper.setPrefWidth(150);
        productDateAndTimeWrapper.setAlignment(Pos.CENTER_LEFT);

        ((VBox)productDateAndTimeWrapper.getChildren().get(0)).getChildren().get(0).getStyleClass().add("productDetailTextStyle");
        ((VBox)productDateAndTimeWrapper.getChildren().get(1)).getChildren().get(0).getStyleClass().add("productDetailTextStyle");

        productRetailPriceWrapper.setPrefWidth(110);
        productRetailPriceWrapper.setAlignment(Pos.CENTER_LEFT);
        productRetailPriceWrapper.getChildren().get(0).getStyleClass().add("productDetailTextStyle");
        productWholesaleWrapper.setPrefWidth(110);
        productWholesaleWrapper.setAlignment(Pos.CENTER_LEFT);
        productWholesaleWrapper.getChildren().get(0).getStyleClass().add("productDetailTextStyle");
        productQuantityWrapper.setPrefWidth(120);
        productQuantityWrapper.setAlignment(Pos.CENTER_LEFT);
        productQuantityWrapper.getChildren().get(0).getStyleClass().add("productDetailTextStyle");

//        productDetailWrapper.setPrefWidth(800);
        productDetailWrapper.setPrefHeight(60);
        productDetailWrapper.setMaxHeight(60);
        productDetailWrapper.setMinHeight(60);
        productDetailWrapper.setAlignment(Pos.CENTER_LEFT);
        productDetailWrapper.getStyleClass().add("productDetailWrapperStyle");
//        DropShadow dropShadow = new DropShadow();
//        dropShadow.setColor(Color.BLACK);
//        dropShadow.setSpread(0.1);
//        dropShadow.setWidth(50);
//        dropShadow.setHeight(50);
//        productDetailWrapper.setEffect(dropShadow);

        Pane gap = new Pane();
        HBox.setHgrow(gap, Priority.ALWAYS);

        productDetailWrapper.getChildren().addAll(productNameWrapper,categoryWrapper,productDateAndTimeWrapper,productRetailPriceWrapper,productWholesaleWrapper,
                gap,productQuantityWrapper);
        return productDetailWrapper;
    }

    public static Product getAddProduct(ComboBox<String> category) {
        Product product = new Product();
        Stage stage = new Stage();
        VBox root = new VBox();
        stage.setScene(new Scene(root,450,540));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        root.getStylesheets().add(Main.class.getResource("assets/stylesheets/AddProductStyles.css").toExternalForm());
        root.getStyleClass().add("rootStyle");
        Label title = new Label("New Product");
        title.getStyleClass().add("titleStyle");

        VBox nameFieldWrapper = new VBox();
        TextField nameField = new TextField();
        Label nameLabel = new Label("Product name");
//        nameField.setPromptText("Product name");
//        nameField.setFocusTraversable(false);
        nameLabel.getStyleClass().add("fieldLabelStyle");
        nameFieldWrapper.getChildren().addAll(nameLabel,nameField);
        nameField.getStyleClass().add("fieldStyle");
        nameFieldWrapper.getStyleClass().add("fieldWrapperStyle");

        VBox categoryFieldWrapper = new VBox();
        TextField categoryField = new TextField();
        Label categoryLabel = new Label("Category");
//        categoryField.setPromptText("Category");
//        categoryField.setFocusTraversable(false);
        categoryLabel.getStyleClass().add("fieldLabelStyle");
        categoryFieldWrapper.getChildren().addAll(categoryLabel,categoryField);
        categoryField.getStyleClass().add("fieldStyle");
        categoryFieldWrapper.getStyleClass().add("fieldWrapperStyle");

        VBox rpFieldWrapper = new VBox();
        TextField rpField = new TextField();
        Label rpLabel = new Label("Retail price");
//        rpField.setPromptText("Retail price");
//        rpField.setFocusTraversable(false);
        rpLabel.getStyleClass().add("fieldLabelStyle");
        rpFieldWrapper.getChildren().addAll(rpLabel,rpField);
        rpField.getStyleClass().add("fieldStyle");
        rpFieldWrapper.getStyleClass().add("fieldWrapperStyle");

        VBox wsFieldWrapper = new VBox();
        TextField wsField = new TextField();
        Label wsLabel = new Label("Wholesale price");
//        wsField.setPromptText("Wholesale price");
//        wsField.setFocusTraversable(false);
        wsLabel.getStyleClass().add("fieldLabelStyle");
        wsFieldWrapper.getChildren().addAll(wsLabel,wsField);
        wsField.getStyleClass().add("fieldStyle");
        wsFieldWrapper.getStyleClass().add("fieldWrapperStyle");

        VBox pqFieldWrapper = new VBox();
        TextField pqField = new TextField();
        Label pqLabel = new Label("Product quantity");
//        pqField.setPromptText("Product quantity");
        pqField.setFocusTraversable(false);
        pqLabel.getStyleClass().add("fieldLabelStyle");
        pqFieldWrapper.getChildren().addAll(pqLabel,pqField);
        pqField.getStyleClass().add("fieldStyle");
        pqFieldWrapper.getStyleClass().add("fieldWrapperStyle");

        HBox buttonWrapper = new HBox();
        Label errorLabel = new Label("Field can't be empty");
        Button addButton = new Button("ADD");
        Pane gap = new Pane();
        HBox.setHgrow(gap,Priority.ALWAYS);
        addButton.setFocusTraversable(false);
        Button cancelButton = new Button("CANCEL");
        cancelButton.setFocusTraversable(false);
        buttonWrapper.getChildren().addAll(errorLabel,gap,addButton,cancelButton);
        buttonWrapper.getStyleClass().add("buttonWrapperStyle");
        buttonWrapper.setSpacing(15);
        errorLabel.getStyleClass().add("errorLabelStyle");
        errorLabel.setVisible(false);
        addButton.getStyleClass().add("buttonStyles");
        addButton.setOnMouseClicked( e -> {
            if ( nameField.getText().isEmpty() || categoryField.getText().isEmpty() || rpField.getText().isEmpty() ||
                    wsField.getText().isEmpty() || pqField.getText().isEmpty() ) {
                errorLabel.setText("Field can't be empty");
                errorLabel.setVisible(true);
            } else {
                errorLabel.setVisible(false);
                errorLabel.setText("Field can't be empty");
                product.setName(nameField.getText().trim());
                product.setCategory(categoryField.getText().trim());
                product.setStringDate(SystemManipulator.getCurrentDateInStringFormat());
                product.setStringTime(SystemManipulator.getCurrentTimeInStringFormat());
                product.setRetailPrice(rpField.getText().trim());
                product.setWholesalePrice(wsField.getText().trim());
                try {
                    product.setProductQuantity(Integer.parseInt(pqField.getText().trim()));
                    // Close current stage cause of all success
                    stage.close();
                } catch ( NumberFormatException nfe ) {
                    product.setName("");
                    errorLabel.setText("Quantity must be digits");
                    errorLabel.setVisible(true);
                }

            }
        });
        cancelButton.getStyleClass().add("buttonStyles");
        cancelButton.setOnMouseClicked( e -> {
            stage.close();
        });

        nameField.setOnKeyReleased( key -> { if ( key.getCode() == KeyCode.TAB ) setFocus(true,categoryField); });
        categoryField.setOnKeyReleased( key -> { if ( key.getCode() == KeyCode.TAB ) setFocus(true,rpField); });
        rpField.setOnKeyReleased( key -> { if ( key.getCode() == KeyCode.TAB ) setFocus(true,wsField); });
        wsField.setOnKeyReleased( key -> { if ( key.getCode() == KeyCode.TAB ) setFocus(true,pqField); });

        root.setSpacing(10);
        root.getChildren().addAll(title,nameFieldWrapper,categoryFieldWrapper,rpFieldWrapper,wsFieldWrapper,pqFieldWrapper,buttonWrapper);
        stage.setTitle("Add New Product");
        stage.getIcons().add(new Image(Main.class.getResource("assets/images/add-product.png").toString()));
        stage.showAndWait();

        System.out.println(product.getName());
        if ( product.getName() != null ) {
            if ( product.getName().equals("") ) {
                return null;
            } else {
                SystemManipulator.exportProductDetail(product);
                SystemManipulator.updateCategory(category);
                return product;
            }
        } else {
            return null;
        }
    }

    public static void updateProductWrapperUIByCategory(VBox productsWrapper , String selectedCategory) {
        productsWrapper.getChildren().clear();
        for ( Product product : SystemManipulator.getSelectedProductsByCategory(selectedCategory) ) {
            HBox productDetailWrapper = getProductDetailWrapper(product);
            if ( product.getProductQuantity() <= MIN_STOCK_VALUE ) productDetailWrapper.getStyleClass().add("lowStockStyle");
            productsWrapper.getChildren().add(productDetailWrapper);
        }
    }

    public static void updateProductWrapperUIByName(VBox productsWrapper , String selectedProductName) {
        productsWrapper.getChildren().clear();
        for ( Product product : SystemManipulator.getSelectedProductsByName(selectedProductName) ) {
            HBox productDetailWrapper = getProductDetailWrapper(product);
            if ( product.getProductQuantity() <= MIN_STOCK_VALUE ) productDetailWrapper.getStyleClass().add("lowStockStyle");
            productsWrapper.getChildren().add(productDetailWrapper);
        }
    }

    public static void updateSelectableProduct(VBox productsWrapper) {

        for ( Node productDetailWrapper : productsWrapper.getChildren() ) {
            HBox currentProductDetailWrapper = (HBox) productDetailWrapper;
            currentProductDetailWrapper.setOnMouseEntered( e -> {
                if ( currentProductDetailWrapper.getStyleClass().contains("lowStockStyle") ) {
                    currentProductDetailWrapper.getStyleClass().add("productDetailWrapperLowStockHoverStyle");
                } else {
                    currentProductDetailWrapper.getStyleClass().add("productDetailWrapperHoverStyle");
                }
                AnimationStyles.translateEffectTopDown(currentProductDetailWrapper,200,1,false,0,-3);
            });
            currentProductDetailWrapper.setOnMouseExited( e -> {
                if ( currentProductDetailWrapper.getStyleClass().contains("lowStockStyle") ) {
                    currentProductDetailWrapper.getStyleClass().removeIf( styleClass -> styleClass.equalsIgnoreCase("productDetailWrapperLowStockHoverStyle"));
                } else {
                    currentProductDetailWrapper.getStyleClass().removeIf( styleClass -> styleClass.equalsIgnoreCase("productDetailWrapperHoverStyle"));
                }
                AnimationStyles.translateEffectTopDown(currentProductDetailWrapper,200,1,false,-3,0);
            });
            currentProductDetailWrapper.setOnMouseClicked( e -> {
                // If this wrapper is new to click...
                if ( clickedProductDetailWrapper == null ) {

                    if ( currentProductDetailWrapper.getStyleClass().contains("lowStockStyle") ) {
                        currentProductDetailWrapper.getStyleClass().add("productDetailWrapperLowStockClickStyle");
                    } else {
                        currentProductDetailWrapper.getStyleClass().add("productDetailWrapperClickStyle");
                    }
                    clickedProductDetailWrapper = currentProductDetailWrapper;
                }
                // If this wrapper is already clicked...
                else if ( clickedProductDetailWrapper == currentProductDetailWrapper ) {

                    if ( currentProductDetailWrapper.getStyleClass().contains("lowStockStyle") ) {
                        currentProductDetailWrapper.getStyleClass().removeIf(sc -> sc.equalsIgnoreCase("productDetailWrapperLowStockClickStyle"));
                    } else {
                        currentProductDetailWrapper.getStyleClass().removeIf(sc -> sc.equalsIgnoreCase("productDetailWrapperClickStyle"));
                    }
                    clickedProductDetailWrapper = null;
                }
                // If this wrapper is not clicked but user already clicked on another wrapper...
                else {

                    if ( currentProductDetailWrapper.getStyleClass().contains("lowStockStyle") ) {
                        clickedProductDetailWrapper.getStyleClass().removeIf(sc -> sc.equalsIgnoreCase("productDetailWrapperLowStockClickStyle"));
                        clickedProductDetailWrapper.getStyleClass().removeIf(sc -> sc.equalsIgnoreCase("productDetailWrapperClickStyle"));
                        currentProductDetailWrapper.getStyleClass().add("productDetailWrapperLowStockClickStyle");
                    } else {
                        clickedProductDetailWrapper.getStyleClass().removeIf(sc -> sc.equalsIgnoreCase("productDetailWrapperLowStockClickStyle"));
                        clickedProductDetailWrapper.getStyleClass().removeIf(sc -> sc.equalsIgnoreCase("productDetailWrapperClickStyle"));
                        currentProductDetailWrapper.getStyleClass().add("productDetailWrapperClickStyle");
                    }
                    clickedProductDetailWrapper = currentProductDetailWrapper;
                }
            });
        }

    }

    public static void deleteProduct(HBox selectedProduct) {
        Product product = new Product();

        Stage stage = new Stage();
        VBox root = new VBox();
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER_LEFT);
        root.getStylesheets().add(Main.class.getResource("assets/stylesheets/DeleteProductStyles.css").toExternalForm());
        root.getStyleClass().add("root");
        Label title = new Label("Are you sure you want to delete?");
        title.getStyleClass().add("titleStyle");
        HBox titleWrapper = new HBox(title);
        Label productName = new Label("Product name - "+getStringFromLabelWrappers(selectedProduct,PRODUCT_NAME));
        productName.getStyleClass().add("productNameStyle");
        HBox productNameWrapper = new HBox(productName);
        HBox buttonWrapper = new HBox();
        Button yesButton = new Button("YES");
        Button noButton = new Button("NO");
        yesButton.getStyleClass().add("buttonStyles");
        yesButton.getStyleClass().add("yesButtonStyle");
        noButton.getStyleClass().add("buttonStyles");
        noButton.getStyleClass().add("noButtonStyle");
        buttonWrapper.getChildren().addAll(yesButton,noButton);
        buttonWrapper.setSpacing(10);
        buttonWrapper.setAlignment(Pos.CENTER_RIGHT);

        yesButton.setOnMouseClicked( e -> {
            product.setName(getStringFromLabelWrappers(selectedProduct,PRODUCT_NAME));
            product.setCategory(getStringFromLabelWrappers(selectedProduct,PRODUCT_CATEGORY));
            product.setStringDate(getStringFromLabelWrappers(selectedProduct,PRODUCT_DATE));
            product.setStringTime(getStringFromLabelWrappers(selectedProduct,PRODUCT_TIME));
            product.setRetailPrice(getStringFromLabelWrappers(selectedProduct,PRODUCT_RETAIL_PRICE));
            product.setWholesalePrice(getStringFromLabelWrappers(selectedProduct,PRODUCT_WHOLESALE_PRICE));
            product.setProductQuantity(Integer.parseInt(getStringFromLabelWrappers(selectedProduct,PRODUCT_QUANTITY)));
            SystemManipulator.deleteProductFromExportedFile(product);
            updateUI();
            clickedProductDetailWrapper = null;
            stage.close();
        });

        noButton.setOnMouseClicked( e -> {
            stage.close();
        });

        root.getChildren().addAll(titleWrapper,productNameWrapper,buttonWrapper);
        Scene scene = new Scene(root,400,150);
        stage.setScene(scene);
        stage.setTitle("Delete Product");
        stage.getIcons().add(new Image(Main.class.getResource("assets/images/remove-product.png").toString()));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.showAndWait();

    }

    public static Product updateProduct() {
        Product newProduct = new Product();
        Product oldProduct = new Product(
                getStringFromLabelWrappers(clickedProductDetailWrapper,PRODUCT_NAME),getStringFromLabelWrappers(clickedProductDetailWrapper,PRODUCT_CATEGORY),
                getStringFromLabelWrappers(clickedProductDetailWrapper,PRODUCT_DATE),getStringFromLabelWrappers(clickedProductDetailWrapper,PRODUCT_TIME),
                getStringFromLabelWrappers(clickedProductDetailWrapper,PRODUCT_RETAIL_PRICE),getStringFromLabelWrappers(clickedProductDetailWrapper,PRODUCT_WHOLESALE_PRICE),
                Integer.parseInt(getStringFromLabelWrappers(clickedProductDetailWrapper,PRODUCT_QUANTITY))
        );
        Stage stage = new Stage();
        VBox root = new VBox();
        stage.setScene(new Scene(root,450,540));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        root.getStylesheets().add(Main.class.getResource("assets/stylesheets/AddProductStyles.css").toExternalForm());
        root.getStyleClass().add("rootStyle");
        Label title = new Label("Update Product");
        title.getStyleClass().add("titleStyle");

        VBox nameFieldWrapper = new VBox();
        TextField nameField = new TextField();
        Label nameLabel = new Label("Product name");
        nameField.setText(oldProduct.getName());
        setFocus(false,nameField);
        nameLabel.getStyleClass().add("fieldLabelStyle");
        nameFieldWrapper.getChildren().addAll(nameLabel,nameField);
        nameField.getStyleClass().add("fieldStyle");
        nameFieldWrapper.getStyleClass().add("fieldWrapperStyle");

        VBox categoryFieldWrapper = new VBox();
        TextField categoryField = new TextField();
        Label categoryLabel = new Label("Category");
        categoryField.setText(oldProduct.getCategory());
        setFocus(false,categoryField);
        categoryLabel.getStyleClass().add("fieldLabelStyle");
        categoryFieldWrapper.getChildren().addAll(categoryLabel,categoryField);
        categoryField.getStyleClass().add("fieldStyle");
        categoryFieldWrapper.getStyleClass().add("fieldWrapperStyle");

        VBox rpFieldWrapper = new VBox();
        TextField rpField = new TextField();
        Label rpLabel = new Label("Retail price");
        rpField.setText(oldProduct.getRetailPrice());
        setFocus(false,rpField);
        rpLabel.getStyleClass().add("fieldLabelStyle");
        rpFieldWrapper.getChildren().addAll(rpLabel,rpField);
        rpField.getStyleClass().add("fieldStyle");
        rpFieldWrapper.getStyleClass().add("fieldWrapperStyle");

        VBox wsFieldWrapper = new VBox();
        TextField wsField = new TextField();
        Label wsLabel = new Label("Wholesale price");
        wsField.setText(oldProduct.getWholesalePrice());
        setFocus(false,wsField);
        wsLabel.getStyleClass().add("fieldLabelStyle");
        wsFieldWrapper.getChildren().addAll(wsLabel,wsField);
        wsField.getStyleClass().add("fieldStyle");
        wsFieldWrapper.getStyleClass().add("fieldWrapperStyle");

        VBox pqFieldWrapper = new VBox();
        TextField pqField = new TextField();
        Label pqLabel = new Label("Product quantity");
        pqField.setText(String.valueOf(oldProduct.getProductQuantity()));
        setFocus(true,pqField);
        pqLabel.getStyleClass().add("fieldLabelStyle");
        pqFieldWrapper.getChildren().addAll(pqLabel,pqField);
        pqField.getStyleClass().add("fieldStyle");
        pqFieldWrapper.getStyleClass().add("fieldWrapperStyle");

        HBox buttonWrapper = new HBox();
        Label errorLabel = new Label("Field can't be empty");
        Button addButton = new Button("UPDATE");
        Pane gap = new Pane();
        HBox.setHgrow(gap,Priority.ALWAYS);
        addButton.setFocusTraversable(false);
        Button cancelButton = new Button("CANCEL");
        cancelButton.setFocusTraversable(false);
        buttonWrapper.getChildren().addAll(errorLabel,gap,addButton,cancelButton);
        buttonWrapper.getStyleClass().add("buttonWrapperStyle");
        buttonWrapper.setSpacing(15);
        errorLabel.getStyleClass().add("errorLabelStyle");
        errorLabel.setVisible(false);
        addButton.getStyleClass().add("buttonStyles");
        addButton.setOnMouseClicked( e -> {
            if ( nameField.getText().isEmpty() || categoryField.getText().isEmpty() || rpField.getText().isEmpty() ||
                    wsField.getText().isEmpty() || pqField.getText().isEmpty() ) {
                errorLabel.setText("Field can't be empty");
                errorLabel.setVisible(true);
            } else {
                errorLabel.setVisible(false);
                errorLabel.setText("Field can't be empty");
                newProduct.setName(nameField.getText().trim());
                newProduct.setCategory(categoryField.getText().trim());
                newProduct.setStringDate(SystemManipulator.getCurrentDateInStringFormat());
                newProduct.setStringTime(SystemManipulator.getCurrentTimeInStringFormat());
                newProduct.setRetailPrice(rpField.getText().trim());
                newProduct.setWholesalePrice(wsField.getText().trim());
                try {
                    newProduct.setProductQuantity(Integer.parseInt(pqField.getText().trim()));
                    // Close current stage cause of all success
                    stage.close();
                } catch ( NumberFormatException nfe ) {
                    newProduct.setName("");
                    errorLabel.setText("Quantity must be digits");
                    errorLabel.setVisible(true);
                }

            }
        });
        cancelButton.getStyleClass().add("buttonStyles");
        cancelButton.setOnMouseClicked( e -> {
            stage.close();
        });

        nameField.setOnKeyReleased( key -> { if ( key.getCode() == KeyCode.TAB ) setFocus(true,categoryField); });
        categoryField.setOnKeyReleased( key -> { if ( key.getCode() == KeyCode.TAB ) setFocus(true,rpField); });
        rpField.setOnKeyReleased( key -> { if ( key.getCode() == KeyCode.TAB ) setFocus(true,wsField); });
        wsField.setOnKeyReleased( key -> { if ( key.getCode() == KeyCode.TAB ) setFocus(true,pqField); });

        root.setSpacing(10);
        root.getChildren().addAll(title,nameFieldWrapper,categoryFieldWrapper,rpFieldWrapper,wsFieldWrapper,pqFieldWrapper,buttonWrapper);
        stage.setTitle("Update Existing Product");
        stage.getIcons().add(new Image(Main.class.getResource("assets/images/update-product.png").toString()));
        stage.showAndWait();

        if ( newProduct.getName() != null ) {
            if ( newProduct.getName().equals("") ) {
                return null;
            } else {
                SystemManipulator.updateProductFromExportedFile(oldProduct,newProduct);
                return newProduct;
            }
        } else {
            return null;
        }
    }

    private static String getStringFromLabelWrappers(HBox parent,int wrapperIndex) {
        String extractedString="";
        VBox childWrapper;
        VBox subChild;
        switch (wrapperIndex) {
            case 0 -> {
                childWrapper = (VBox) parent.getChildren().get(0);
                extractedString = ((Label) childWrapper.getChildren().get(0)).getText();
            }
            case 1 -> {
                childWrapper = (VBox) parent.getChildren().get(1);
                extractedString = ((Label) childWrapper.getChildren().get(0)).getText();
            }
            case 2 -> {
                childWrapper = (VBox) parent.getChildren().get(2);
                subChild = (VBox) childWrapper.getChildren().get(0);
                extractedString = ((Label) subChild.getChildren().get(0)).getText();
            }
            case 3 -> {
                childWrapper = (VBox) parent.getChildren().get(2);
                subChild = (VBox) childWrapper.getChildren().get(1);
                extractedString = ((Label) subChild.getChildren().get(0)).getText();
            }
            case 4 -> {
                childWrapper = (VBox) parent.getChildren().get(3);
                extractedString = ((Label) childWrapper.getChildren().get(0)).getText();
            }
            case 5 -> {
                childWrapper = (VBox) parent.getChildren().get(4);
                extractedString = ((Label) childWrapper.getChildren().get(0)).getText();
            }
            case 6 -> {
                childWrapper = (VBox) parent.getChildren().get(6);
                extractedString = ((Label) childWrapper.getChildren().get(0)).getText();
            }
        }
        return extractedString;
    }

    private static Product getExtractedProductFromWrapper(HBox wrapper) {
        return new Product(
                getStringFromLabelWrappers(wrapper,PRODUCT_NAME),
                getStringFromLabelWrappers(wrapper,PRODUCT_CATEGORY),
                getStringFromLabelWrappers(wrapper,PRODUCT_DATE),
                getStringFromLabelWrappers(wrapper,PRODUCT_TIME),
                getStringFromLabelWrappers(wrapper,PRODUCT_RETAIL_PRICE),
                getStringFromLabelWrappers(wrapper,PRODUCT_WHOLESALE_PRICE),
                Integer.parseInt(getStringFromLabelWrappers(wrapper,PRODUCT_QUANTITY))
        );
    }

//    public static int getMaxProdPerCat() {
//        MAX_PROD_PER_CAT_VALUE = 0;
//        int currentCategorySize = 0;
//        for ( String c : category.getItems() ) {
//            if ( !c.equalsIgnoreCase(DEFAULT_CATEGORY_FIRST_ITEM_NAME) ) {
//                currentCategorySize = SystemManipulator.getSelectedProductsByCategory(c).size();
//                if ( currentCategorySize > MAX_PROD_PER_CAT_VALUE ) MAX_PROD_PER_CAT_VALUE = currentCategorySize;
//            }
//        }
//        return MAX_PROD_PER_CAT_VALUE;
//    }

    public static int getMinStockValue() {
        return MIN_STOCK_VALUE;
    }

    public static int getTotalCategory() {
        return category.getItems().size()-2;
    }

    public static int getTotalProduct() {
        return SystemManipulator.getTotalProduct();
    }

    public static int getTotalStock() {
        return SystemManipulator.getTotalStock();
    }

    public static void setMinimumStockValue() {
        Stage stage = new Stage();
        stage.getIcons().add(new Image(Main.class.getResource("assets/images/set.png").toString()));
        HBox root = new HBox();
        root.getStylesheets().add(Main.class.getResource("assets/stylesheets/SetMinStockStyles.css").toExternalForm());
        root.getStyleClass().add("root");
        Label title = new Label("Set Minimum Stock Value");
        title.getStyleClass().add("titleStyle");

        Image setIcon = new Image(Main.class.getResource("assets/images/set.png").toString());
        ImageView setIconWrapper = new ImageView(setIcon);
        setIconWrapper.setFitWidth(40);
        setIconWrapper.setFitHeight(40);
        TextField minStockField = new TextField();
        minStockField.getStyleClass().add("minStockStyle");
        minStockField.setText(String.valueOf(MIN_STOCK_VALUE));
        setFocus(false,minStockField);
        HBox imageAndTextFieldWrapper = new HBox(setIconWrapper,minStockField);

        HBox buttonWrapper = new HBox();
        Button okButton = new Button("OK");
        okButton.getStyleClass().add("buttonStyle");
        buttonWrapper.getChildren().addAll(okButton);
        buttonWrapper.setAlignment(Pos.CENTER_RIGHT);

        imageAndTextFieldWrapper.setAlignment(Pos.CENTER);
        imageAndTextFieldWrapper.setSpacing(20);
        buttonWrapper.setAlignment(Pos.CENTER);

        Pane gap = new Pane();
        HBox.setHgrow(gap,Priority.ALWAYS);

        root.getChildren().addAll(imageAndTextFieldWrapper,gap,buttonWrapper);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        okButton.setOnMouseClicked( e -> {
            try {
                MIN_STOCK_VALUE = Integer.parseInt(minStockField.getText());
                SystemManipulator.exportMinStockValue(MIN_STOCK_VALUE);
                updateUI();
                stage.close();
            } catch ( NumberFormatException nfe ) {
                System.out.println("[Min_Stock_Value_Part] - Please just type digits.");
            }
        });

        Scene scene = new Scene(root,400,100);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Set Minimum Stock");
        stage.showAndWait();


    }

    public static void updateUI() {
        SystemManipulator.updateCategory(category);
        updateSelectableProduct(productsWrapper);
        minStock.setText(String.valueOf(getMinStockValue()));
        totalCategory.setText(String.valueOf(getTotalCategory()));
        totalStock.setText(String.valueOf(getTotalStock()));
        totalProduct.setText(String.valueOf(getTotalProduct()));
        clickedProductDetailWrapper = null;
    }
}

