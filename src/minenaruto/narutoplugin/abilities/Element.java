package minenaruto.narutoplugin.abilities;

public enum Element {
    FIRE("Стихия огня", AvailabilityType.DEFAULT),
    EARTH("Стихия земли", AvailabilityType.DEFAULT),
    LIGHTNING("Стихия молнии", AvailabilityType.DEFAULT),
    WATER("Стихия воды", AvailabilityType.DEFAULT),
    WIND("Стихия ветра", AvailabilityType.DEFAULT),
    LOG("Стихия дерева", AvailabilityType.KKG),
    SHARINGAN("Шаринган", AvailabilityType.EYE),
    RINNENGAN("Ринненгана", AvailabilityType.EYE),
    NONE("Прочие", AvailabilityType.DEFAULT);
    private String name;
    private AvailabilityType type;

    Element(String name, AvailabilityType type) {
        this.name = name;
        this.type = type;
    }
}
