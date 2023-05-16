//package com.example.studentattendance;
//
//import com.example.studentattendance.models.Account;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.Scanner;
//
//public abstract class DataModel<T, K> {
//    protected ArrayList<T> data;
//    private String filePath;
//    private int lastAssignedId;
//
//    public DataModel(String filePath) {
//        this.filePath = filePath;
//        initialize();
//    }
//
//    protected DataModel() {
//        initialize();
//    }
//
//    private void initialize() {
//        File file = new File(filePath);
//        if (file.exists()) {
//            try (Scanner scanner = new Scanner(file)) {
//                String headerLine = scanner.nextLine(); // Read header line
//                data = new ArrayList<>();
//                while (scanner.hasNext()) {
//                    String line = scanner.nextLine();
//                    String[] fields = line.split(",");
//                    T item = createItem(fields);
//                    data.add(item);
//                }
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        } else {
//            data = new ArrayList<>();
//        }
//        lastAssignedId = findLastAssignedId();
//    }
//
//    private int findLastAssignedId() {
//        int maxId = -1;
//        for (T item : data) {
//            K itemId = getItemId(item);
//            if (itemId instanceof Integer) {
//                int intValue = (Integer) itemId;
//                if (intValue > maxId) {
//                    maxId = intValue;
//                }
//            }
//        }
//        return maxId;
//    }
//
//    protected K getItemId(T item) {
//        return null;
//    }
//
//    protected abstract T createItem(String[] fields);
//
//    public void save() {
//        try (PrintWriter pw = new PrintWriter(filePath)) {
//            pw.println(getHeaderLine());
//            for (T item : data) {
//                pw.println(itemToString(item));
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    protected String getHeaderLine() {
//        return "";
//    }
//
//    protected String itemToString(T item) {
//        return "";
//    }
//
//    public void addItem(T item) {
//        K itemId = getItemId(item);
//        if (itemId == null || getItemById(itemId) != null) {
//            lastAssignedId++;
//            setItemId(item, lastAssignedId);
//        }
//        data.add(item);
//    }
//
//    protected void setItemId(T item, int id) {
//        // Implement in the subclass to set the ID for the item
//    }
//
//    public T getItemById(K id) {
//        for (T item : data) {
//            K itemId = getItemId(item);
//            if (Objects.equals(itemId, id)) {
//                return item;
//            }
//        }
//        return null;
//    }
//
//    public T deleteItemById(K id) {
//        T item = getItemById(id);
//        if (item != null) {
//            data.remove(item);
//        }
//        return item;
//    }
//
//    public List<T> getAllItems() {
//        return new ArrayList<>(data);
//    }
//
//    public ArrayList<T> getData() {
//        return data;
//    }
//}
