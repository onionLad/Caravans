public class Item {
    private String name;
    private ItemType itemType;

    Item(String s, ItemType it) {
        this.name = s;
        this.itemType = it;
    }

    public boolean equals(Object o)
    {
        return o.getClass() == Item.class &&
                ((Item) o).name == this.name &&
                ((Item) o).itemType == this.itemType;
    }
    public String toString()
    {
        return name;
    }
    public ItemType itemType()
    {
        return itemType;
    }
}
