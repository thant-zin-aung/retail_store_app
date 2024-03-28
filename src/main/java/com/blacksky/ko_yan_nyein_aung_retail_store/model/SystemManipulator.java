package com.blacksky.ko_yan_nyein_aung_retail_store.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemManipulator {
    private static final int DATE_IN_DAY = 0;
    private static final int DATE_IN_MONTH = 1;
    private static final int DATE_IN_YEAR = 2;
    private static final int TIME_IN_HOUR = 0;
    private static final int TIME_IN_MIN = 1;
    private static final int TIME_IN_AM_PM = 1;
    public static final String MIN_STOCK_VALUE_FILE_NAME = "MinStockValue";
    public static final String PRODUCT_DETAIL_EXPORT_FILE_NAME = "StoreProductList";
    public static final String PRODUCT_DETAIL_EXPORT_TEMP_FILE_NAME = "StoreProductListTemp";
    private static final String APP_DEFAULT_PATH = "C:\\Users\\"+System.getProperty("user.name")+"\\BlackskyRetailStore00001";
    private static final String APP_DEFAULT_BACKUP_PATH = "C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\BlackskyRetailStore00001";
    private static final File APP_RESOURCE_DIRECTORY = new File(APP_DEFAULT_PATH);
    public static final String APP_CATEGORY_PATH = APP_DEFAULT_PATH +"\\category";
    public static final String APP_MIN_STOCK_PATH = APP_DEFAULT_PATH +"\\min-stock";

    private static final String DELIMITER = "~";


    private static void executeSystemCommand(String command) {
        try {
            Runtime.getRuntime().exec("cmd.exe /c "+command);
        } catch ( Exception e ) {
            e.printStackTrace();
            System.out.println("Error in system command execution");
        }
    }

    public static String getCurrentDateInStringFormat() {
        DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
        return df.format(new Date());
    }
    public static String getCurrentTimeInStringFormat() {
        DateFormat df = new SimpleDateFormat("hh:mm a");
        return df.format(new Date());
    }
    private static Date getDateAndTimeInDateFormat(String date, String time) {
        DateFormat df = new SimpleDateFormat("dd MMMM yyyy hh:mm a");
        Date formattedDate = null;
        try {
            formattedDate = df.parse(date+" "+time);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return formattedDate;
//        int dateInInteger = 0;
//        String[] stringDateSplitter = date.split(" ");
//        int day = Integer.parseInt(stringDateSplitter[DATE_IN_DAY]);
//        System.out.println(day);
//        int month = switch (stringDateSplitter[DATE_IN_MONTH]) {
//            case "January" -> 1;
//            case "February" -> 2;
//            case "March" -> 3;
//            case "April" -> 4;
//            case "May" -> 5;
//            case "June" -> 6;
//            case "July" -> 7;
//            case "August" -> 8;
//            case "September" -> 9;
//            case "October" -> 10;
//            case "November" -> 11;
//            case "December" -> 12;
//            default -> 0;
//        };
//        int year = Integer.parseInt(stringDateSplitter[DATE_IN_YEAR]);
//        dateInInteger = (day+month+year);
//
//        String[] stringTimeSplitter = time.split(" ");
//        String[] stringHourSplitter = stringTimeSplitter[0].split(":");
//        String amPm = stringTimeSplitter[TIME_IN_AM_PM];
//        int hour = 0;
//        if ( amPm.equalsIgnoreCase("AM") ) {
//            hour = Integer.parseInt(stringHourSplitter[TIME_IN_HOUR]);
//        } else {
//            hour = Integer.parseInt(stringHourSplitter[TIME_IN_HOUR]+12);
//        }
//        int min = Integer.parseInt(stringHourSplitter[TIME_IN_MIN]);
//        int timeInInteger = hour+min;
//
//        return (day+month+year+hour+min+"");
    }
    public static void createDirectory ( String name ) {
        File directory = new File(name);
        executeSystemCommand("mkdir "+name);
    }
    public static void delayTime(int duration) {
        try {
            Thread.sleep(duration);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }


    public static void exportProductDetail(Product product) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(APP_CATEGORY_PATH +"\\"+ PRODUCT_DETAIL_EXPORT_FILE_NAME,StandardCharsets.UTF_8,true));
            bw.write(product.getName()+DELIMITER+product.getCategory()+DELIMITER+product.getStringDate()+DELIMITER+product.getStringTime()+
                    DELIMITER+product.getRetailPrice()+DELIMITER+product.getWholesalePrice()+DELIMITER+product.getProductQuantity()+"\n");
            bw.close();
        } catch ( Exception e ) {
            System.out.println("Error in export product detail");
        }
    }

    public static void exportMinStockValue(int value) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(APP_MIN_STOCK_PATH +"\\"+ MIN_STOCK_VALUE_FILE_NAME,StandardCharsets.UTF_8));
            bw.write(String.valueOf(value));
            bw.close();
        } catch ( Exception e ) {
            System.out.println("Error in export product detail");
        }

    }

    public static void importMinStockValue() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(APP_MIN_STOCK_PATH +"\\"+ MIN_STOCK_VALUE_FILE_NAME,StandardCharsets.UTF_8));
            String readLine = br.readLine();
            ProductManipulator.MIN_STOCK_VALUE = Integer.parseInt(readLine);
            br.close();
        } catch ( Exception e ) {
            System.out.println("Error in export product detail");
        }

    }

    public static void importProductDetails(VBox productsWrapper,ComboBox<String> category) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(APP_CATEGORY_PATH +"\\"+ PRODUCT_DETAIL_EXPORT_FILE_NAME, StandardCharsets.UTF_8));
            String readDetail;
            String[] productSplitter;
            while ( (readDetail=br.readLine()) != null ) {
                productSplitter = readDetail.split(DELIMITER);
                productsWrapper.getChildren().add(ProductManipulator.getProductDetailWrapper(
                        new Product(productSplitter[ProductManipulator.PRODUCT_NAME],productSplitter[ProductManipulator.PRODUCT_CATEGORY],
                                productSplitter[ProductManipulator.PRODUCT_DATE],productSplitter[ProductManipulator.PRODUCT_TIME],productSplitter[ProductManipulator.PRODUCT_RETAIL_PRICE],productSplitter[ProductManipulator.PRODUCT_WHOLESALE_PRICE],
                                Integer.parseInt(productSplitter[ProductManipulator.PRODUCT_QUANTITY]))
                ));
            }
            br.close();
            updateCategory(category);
        } catch ( Exception e ) {
            System.out.println("Error in import product details");
        }
    }

    public static void updateCategory(ComboBox<String> category) {
//        if ( !category.getItems().contains("All products") ) category.getItems().add("All Products");
        String selectedOldCategory = category.getSelectionModel().getSelectedItem();
        category.getItems().clear();
        category.getItems().add(ProductManipulator.DEFAULT_CATEGORY_FIRST_ITEM_NAME);
        category.getItems().add(ProductManipulator.DEFAULT_CATEGORY_SECOND_ITEM_NAME);
        category.getSelectionModel().selectFirst();


        try ( BufferedReader br = new BufferedReader(new FileReader(
                SystemManipulator.APP_CATEGORY_PATH+"\\"+SystemManipulator.PRODUCT_DETAIL_EXPORT_FILE_NAME,StandardCharsets.UTF_8))) {
            String readLine;
            String[] productSplitter;
            while ( ( readLine = br.readLine() ) != null ) {
                productSplitter = readLine.split(DELIMITER);
                // Just for maxProdPerCat value...
//                currentCategory = productSplitter[ProductManipulator.PRODUCT_CATEGORY];

                if ( !category.getItems().contains(productSplitter[ProductManipulator.PRODUCT_CATEGORY]) ) {
                    boolean searchFlag = false;
                    for ( int id = 0 ; id < category.getItems().size() ; id++ ) {
                        if ( category.getItems().get(id).equalsIgnoreCase(productSplitter[ProductManipulator.PRODUCT_CATEGORY]) ) {
                            searchFlag=true;
                            break;
                        }
                    }
                    if ( !searchFlag ) {
                        category.getItems().add(productSplitter[ProductManipulator.PRODUCT_CATEGORY]);
                    }
                }

            }
        }
        catch ( FileNotFoundException fnfe ) {
            System.out.println(PRODUCT_DETAIL_EXPORT_FILE_NAME+" not found");
        }
        catch ( Exception e ) {
            System.out.println("Error in updating category");
        }

        if ( selectedOldCategory != null ) {
            category.getSelectionModel().select(selectedOldCategory);
            ProductManipulator.updateProductWrapperUIByCategory(ProductManipulator.productsWrapper,selectedOldCategory);
        } else {
            ProductManipulator.updateProductWrapperUIByCategory(ProductManipulator.productsWrapper,ProductManipulator.DEFAULT_CATEGORY_FIRST_ITEM_NAME);
        }
    }

    public static void deleteProductFromExportedFile(Product product) {
        try (BufferedReader br = new BufferedReader(new FileReader(APP_CATEGORY_PATH+"\\"+PRODUCT_DETAIL_EXPORT_FILE_NAME,StandardCharsets.UTF_8));
             BufferedWriter bw = new BufferedWriter(new FileWriter(APP_CATEGORY_PATH+"\\"+PRODUCT_DETAIL_EXPORT_TEMP_FILE_NAME,StandardCharsets.UTF_8))) {
            String readLine;
            while ( ( readLine = br.readLine() ) != null ) {
                if ( readLine.contains(product.getName()) && readLine.contains(product.getCategory()) && readLine.contains(product.getStringDate()) &&
                        readLine.contains(product.getStringTime()) && readLine.contains(product.getRetailPrice()) && readLine.contains(product.getWholesalePrice()) &&
                        readLine.contains(String.valueOf(product.getProductQuantity()))) {
                    continue;
                }
                bw.write(readLine+"\n");
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            System.out.println("Error in reading or writing part of deleting product");
        }

        if ( new File(APP_CATEGORY_PATH+"\\"+PRODUCT_DETAIL_EXPORT_TEMP_FILE_NAME).exists() ) {
            new File(APP_CATEGORY_PATH+"\\"+PRODUCT_DETAIL_EXPORT_FILE_NAME).delete();
//            executeSystemCommand("rename \""+APP_CATEGORY_PATH+"\\"+PRODUCT_DETAIL_EXPORT_TEMP_FILE_NAME+"\" "+PRODUCT_DETAIL_EXPORT_FILE_NAME);
            new File(APP_CATEGORY_PATH+"\\"+PRODUCT_DETAIL_EXPORT_TEMP_FILE_NAME).renameTo(
                    new File(APP_CATEGORY_PATH+"\\"+PRODUCT_DETAIL_EXPORT_FILE_NAME)
            );
        }
    }

    public static void updateProductFromExportedFile(Product oldProduct,Product newProduct) {
        try (BufferedReader br = new BufferedReader(new FileReader(APP_CATEGORY_PATH+"\\"+PRODUCT_DETAIL_EXPORT_FILE_NAME,StandardCharsets.UTF_8));
             BufferedWriter bw = new BufferedWriter(new FileWriter(APP_CATEGORY_PATH+"\\"+PRODUCT_DETAIL_EXPORT_TEMP_FILE_NAME,StandardCharsets.UTF_8))) {
            String readLine;
            while ( ( readLine = br.readLine() ) != null ) {
                if ( readLine.contains(oldProduct.getName()) && readLine.contains(oldProduct.getCategory()) && readLine.contains(oldProduct.getStringDate()) &&
                        readLine.contains(oldProduct.getStringTime()) && readLine.contains(oldProduct.getRetailPrice()) && readLine.contains(oldProduct.getWholesalePrice()) &&
                        readLine.contains(String.valueOf(oldProduct.getProductQuantity()))) {
                    bw.write(newProduct.getName()+DELIMITER+newProduct.getCategory()+DELIMITER+newProduct.getStringDate()+DELIMITER+
                            newProduct.getStringTime()+DELIMITER+newProduct.getRetailPrice()+DELIMITER+newProduct.getWholesalePrice()+
                            DELIMITER+String.valueOf(newProduct.getProductQuantity())+"\n");
                } else {
                    bw.write(readLine+"\n");
                }

            }
        } catch ( Exception e ) {
            e.printStackTrace();
            System.out.println("Error in updating exported product detail...");
        }

        if ( new File(APP_CATEGORY_PATH+"\\"+PRODUCT_DETAIL_EXPORT_TEMP_FILE_NAME).exists() ) {
            new File(APP_CATEGORY_PATH+"\\"+PRODUCT_DETAIL_EXPORT_FILE_NAME).delete();
//            executeSystemCommand("rename \""+APP_CATEGORY_PATH+"\\"+PRODUCT_DETAIL_EXPORT_TEMP_FILE_NAME+"\" "+PRODUCT_DETAIL_EXPORT_FILE_NAME);
            new File(APP_CATEGORY_PATH+"\\"+PRODUCT_DETAIL_EXPORT_TEMP_FILE_NAME).renameTo(
                    new File(APP_CATEGORY_PATH+"\\"+PRODUCT_DETAIL_EXPORT_FILE_NAME)
            );
        }
    }

    public static ObservableList<Product> getSelectedProductsByCategory(String selectedCategory) {
        ObservableList<Product> selectedProducts = FXCollections.observableArrayList();
        Product tempProduct = null;
        try ( BufferedReader br = new BufferedReader(new FileReader(APP_CATEGORY_PATH+"\\"+PRODUCT_DETAIL_EXPORT_FILE_NAME,StandardCharsets.UTF_8))) {
            String readProduct;
            String[] productSplitter;
            while ( (readProduct = br.readLine() ) != null ) {
                productSplitter = readProduct.split(DELIMITER);

                tempProduct = new Product(
                        productSplitter[ProductManipulator.PRODUCT_NAME],productSplitter[ProductManipulator.PRODUCT_CATEGORY],productSplitter[ProductManipulator.PRODUCT_DATE],productSplitter[ProductManipulator.PRODUCT_TIME],
                        productSplitter[ProductManipulator.PRODUCT_RETAIL_PRICE],productSplitter[ProductManipulator.PRODUCT_WHOLESALE_PRICE],Integer.parseInt(productSplitter[ProductManipulator.PRODUCT_QUANTITY])
                );

                if ( selectedCategory.equalsIgnoreCase(ProductManipulator.DEFAULT_CATEGORY_SECOND_ITEM_NAME ) ) {
                    if ( tempProduct.getProductQuantity() <= ProductManipulator.getMinStockValue() ) {
                        selectedProducts.add(tempProduct);
                    }
                } else if ( productSplitter[ProductManipulator.PRODUCT_CATEGORY].equalsIgnoreCase(selectedCategory) ||
                        selectedCategory.equalsIgnoreCase(ProductManipulator.DEFAULT_CATEGORY_FIRST_ITEM_NAME) ) {
                    selectedProducts.add(tempProduct);
                }
            }
        }
        catch ( FileNotFoundException fnfe ) {
            System.out.println(PRODUCT_DETAIL_EXPORT_FILE_NAME+" not found");
        }
        catch ( Exception e ) {
//            e.printStackTrace();
            System.out.println("Error in getting selected products by category...");

        }

        selectedProducts.sort( (p1,p2) -> getDateAndTimeInDateFormat(p2.getStringDate(),p2.getStringTime()).compareTo(
                getDateAndTimeInDateFormat(p1.getStringDate(),p1.getStringTime())));
        return selectedProducts;
    }

    public static ObservableList<Product> getSelectedProductsByName(String selectedProductName) {
        ObservableList<Product> selectedProducts = FXCollections.observableArrayList();
        try ( BufferedReader br = new BufferedReader(new FileReader(APP_CATEGORY_PATH+"\\"+PRODUCT_DETAIL_EXPORT_FILE_NAME,StandardCharsets.UTF_8))) {
            String readProduct;
            String[] productSplitter;
            while ( (readProduct = br.readLine() ) != null ) {
                productSplitter = readProduct.split(DELIMITER);
                if ( productSplitter[ProductManipulator.PRODUCT_NAME].toLowerCase().contains(selectedProductName.toLowerCase()) ) {

                    selectedProducts.add(new Product(
                            productSplitter[ProductManipulator.PRODUCT_NAME],productSplitter[ProductManipulator.PRODUCT_CATEGORY],productSplitter[ProductManipulator.PRODUCT_DATE],productSplitter[ProductManipulator.PRODUCT_TIME],
                            productSplitter[ProductManipulator.PRODUCT_RETAIL_PRICE],productSplitter[ProductManipulator.PRODUCT_WHOLESALE_PRICE],Integer.parseInt(productSplitter[ProductManipulator.PRODUCT_QUANTITY])
                    ));

                }
            }
        } catch ( Exception e ) {
            System.out.println("Error in getting selected products by name...");

        }
        return selectedProducts;
    }

    public static int getTotalProduct() {
        int totalProduct = 0;
        try ( BufferedReader br = new BufferedReader(new FileReader(APP_CATEGORY_PATH+"\\"+PRODUCT_DETAIL_EXPORT_FILE_NAME,StandardCharsets.UTF_8)))
        {
            while (br.readLine() != null) {
                ++totalProduct;
            }
        } catch ( Exception e ) {
            System.out.println("Error in systemmanipulator getTotalProduct");
        }
        return totalProduct;
    }

    public static int getTotalStock() {
        int totalStock = 0;
        String[] productSplitter;
        String readline;
        try ( BufferedReader br = new BufferedReader(new FileReader(APP_CATEGORY_PATH+"\\"+PRODUCT_DETAIL_EXPORT_FILE_NAME,StandardCharsets.UTF_8)))
        {
            while ((readline = br.readLine()) != null) {
                productSplitter = readline.split(DELIMITER);
                totalStock += Integer.parseInt(productSplitter[ProductManipulator.PRODUCT_QUANTITY]);
            }
        } catch ( Exception e ) {
            System.out.println("Error in systemmanipulator getTotalStock");
        }
        return totalStock;
    }

    public static void lockAppResourceFiles() {

        if ( APP_RESOURCE_DIRECTORY.exists() ) {
            executeSystemCommand("attrib +s +r +h \""+APP_DEFAULT_PATH+"\"");
            executeSystemCommand("echo y| cacls \""+APP_DEFAULT_PATH+"\" /c /p everyone:n");
        }
    }

    public static void unlockAppResourceFiles() {
        if ( APP_RESOURCE_DIRECTORY.exists() ) {
            executeSystemCommand("echo y| cacls \""+APP_DEFAULT_PATH+"\" /c /p everyone:f");
            executeSystemCommand("attrib -s -r -h \""+APP_DEFAULT_PATH+"\"");
        }
    }

    public static void backupAppResourceFiles() {
        if ( APP_RESOURCE_DIRECTORY.exists() ) {
            unlockAppResourceFiles();
            executeSystemCommand("echo y| cacls \""+APP_DEFAULT_BACKUP_PATH+"\" /c /p everyone:f");
            executeSystemCommand("attrib -s -r -h \""+APP_DEFAULT_BACKUP_PATH+"\"");
            executeSystemCommand("robocopy \""+APP_DEFAULT_PATH+"\" \""+APP_DEFAULT_BACKUP_PATH+"\" /E");
            executeSystemCommand("attrib +s +r +h \""+APP_DEFAULT_BACKUP_PATH+"\"");
            executeSystemCommand("echo y| cacls \""+APP_DEFAULT_BACKUP_PATH+"\" /c /p everyone:n");
            lockAppResourceFiles();
        }

    }



}
