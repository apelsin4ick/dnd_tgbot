public class Personazhi {
    Long id;
    Long peapleId;
    String name;
    String classes;
    String races;
    Long strength;
    Long dexterity;
    Long endurance;
    Long intellect;
    Long wisdom;
    Long charisma;

    public Personazhi(Long id, Long peapleId, String name, String classes, String races, Long strength, Long dexterity, Long endurance, Long intellect, Long wisdom, Long charisma) {
        this.id = id;
        this.peapleId = peapleId;
        this.name = name;
        this.classes = classes;
        this.races = races;
        this.strength = strength;
        this.dexterity = dexterity;
        this.endurance = endurance;
        this.intellect = intellect;
        this.wisdom = wisdom;
        this.charisma = charisma;
    }

    @Override
    public String toString() {
        return "персонаж: " + id + "\n" +
                " peapleId=" + peapleId + "\n" +
                " имя=" + name  + "\n" +
                " класс=" + classes  + "\n" +
                " рассы=" + races  + "\n" +
                " сила=" + strength + "\n" +
                " ловкость=" + dexterity + "\n" +
                " выносливость=" + endurance + "\n" +
                " интилект=" + intellect + "\n" +
                " мудрость=" + wisdom + "\n" +
                " харизма=" + charisma + "\n";
    }
}
