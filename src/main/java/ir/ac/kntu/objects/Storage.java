package ir.ac.kntu.objects;

import java.util.ArrayList;
import java.util.Objects;

public class Storage {

    private final ArrayList<Commodity> storage = new ArrayList<>();

    public Storage() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Storage storage1)) return false;
        return getStorage().equals(storage1.getStorage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStorage());
    }

    public void addToStorage(Commodity commodity) {
        if (!storage.contains(commodity)) {
            this.storage.add(commodity);
        } else {
            storage.get(storage.indexOf(commodity)).setQuantity
                    (String.valueOf(Integer.parseInt(storage.get(storage.indexOf(commodity)).getQuantity()) + 1));
        }
    }

    public ArrayList<Commodity> getStorage() {
        return storage;
    }

    public void removeFromStorage(Commodity commodity) {
        if (!storage.contains(commodity)) {
            System.out.println("this commodity does not exist in the storage! \n you can not sell it");
        } else {
            getStorage().get(getStorage().indexOf(commodity)).setQuantity(String.valueOf
                    (Integer.parseInt(getStorage().get(getStorage().indexOf(commodity)).getQuantity()) - 1));
            if (getStorage().get(getStorage().indexOf(commodity)).getQuantity().equals("0")) {
                getStorage().remove(getStorage().get(getStorage().indexOf(commodity)));
            }
        }
    }
}
