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
        // Сортування за кличкою за зменшенням (desc), при рівності — темперамент за зростанням
        int nicknameComparison = other.nickname.compareTo(this.nickname);
        if (nicknameComparison != 0) return nicknameComparison;
        return this.temperament.compareTo(other.temperament);
    }

    @Override
    public String toString() {
        return "Hedgehog{nickname='" + nickname + "', temperament='" + temperament + "'}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, temperament);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hedgehog hedgehog = (Hedgehog) o;
        return Objects.equals(nickname, hedgehog.nickname) &&
               Objects.equals(temperament, hedgehog.temperament);
    }
}
