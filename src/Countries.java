/**
 * Created by Robert on 10/03/17.
 */
import java.util.ArrayList;
import java.util.List;

public class Countries {
    private List<Country> countries;

    public Countries() {
        countries = new ArrayList<>();
    }

    public void addCountry(String name, String iso2code) {
        countries.add(new Country(name, iso2code));
    }

    private int getIndexOfCountryByIso2Code(String iso2code) {
        for(Country country: countries){
            if(country.getIso2Code().equals(iso2code)){
                return countries.indexOf(country);
            }
        }
        return -1;
    }

    public void setRegion(String iso2code, String region) {
        int index = getIndexOfCountryByIso2Code(iso2code);
        if(index != -1){
            countries.get(index).setRegion(region);
        }
    }

    public void setIncomeLevel(String iso2code, String incomeLevel) {
        int index = getIndexOfCountryByIso2Code(iso2code);
        if(index != -1){
            countries.get(index).setIncomeLevel(incomeLevel);
        }
    }

    public void setLifeExpectancy(String iso2code, String lifeExpectancy) {
        int index = getIndexOfCountryByIso2Code(iso2code);
        if(index != -1){
            countries.get(index).setLifeExpectancy(Double.parseDouble(lifeExpectancy));
        }
    }

    public String report() {
        String report = "";
        for(Country country: countries){
            report += country.getReportRow() + "\n";
        }
        return report;
    }
}
