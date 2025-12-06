import java.util.Objects;

public class Hedgehog implements Comparable<Hedgehog> {
    private String nickname;
    private String temperament;

    public Hedgehog(String nickname, String temperament) {
        this.nickname = nickname;
        this.temperament = temperament;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTemperament() {
        return temperament;
    }

    @Override
    public int compareTo(Hedgehog other) {
        // Сортуємо спочатку за кличкою (за зменшенням), потім за темпераментом (за зростанням)
        int nicknameComparison = other.nickname.compareTo(this.nickname); // За зменшенням
        if (nicknameComparison != 0) {
            return nicknameComparison;
        }
        return this.temperament.compareTo(other.temperament); // За зростанням
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Hedgehog hedgehog = (Hedgehog) obj;
        return Objects.equals(nickname, hedgehog.nickname) &&
               Objects.equals(temperament, hedgehog.temperament);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, temperament);
    }

    @Override
    public String toString() {
        return "Hedgehog{nickname='" + nickname + "', temperament='" + temperament + "'}";
    }
}
