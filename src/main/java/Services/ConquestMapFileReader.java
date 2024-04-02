package Services;

import Models.Continent;
import Models.Country;
import Models.CurrentState;
import Models.Map;

import java.util.ArrayList;
import java.util.List;

public class ConquestMapFileReader {
    public void readConquestFile(CurrentState p_currentState, Map p_map, List<String> p_fileLines, String p_fileName) {
        List<String> l_continentData = getMetaData(p_fileLines, "continent");

        List<Continent> l_continentList = parseContinentMetaData(l_continentData);
        List<String> l_countryData = getMetaData(p_fileLines, "country");
        List<Country> l_countryList = parseCountryMetaData(l_countryData,l_continentList);
        List<Country> l_updatedCountryList = parseNeighboursMetaData(l_countryList, l_countryData);

        l_continentList = linkCountryToContinent(l_continentList, l_updatedCountryList);
        p_map.setD_mapContinents(l_continentList);
        p_map.setD_mapCountries(l_updatedCountryList);
        p_map.setD_mapName(p_fileName);
        p_currentState.setD_map(p_map);
    }

    private List<Continent> linkCountryToContinent(List<Continent> p_continentList, List<Country> p_updatedCountryList) {
        for(Country l_eachCountry : p_updatedCountryList){
            for(Continent l_eachContinent : p_continentList){
                if(l_eachContinent.getD_continentID().equals(l_eachCountry.getD_continentID())){
                    l_eachContinent.addCountry(l_eachCountry);
                }
            }
        }
        return p_continentList;
    }

    private List<Country> parseNeighboursMetaData(List<Country> p_countryList, List<String> p_countryData) {
        List<Country> l_updatedCountryList = new ArrayList<>(p_countryList);
        String l_matchedCountry = null;
        for(Country l_eachCountry : l_updatedCountryList){
            for (String l_eachCountryData : p_countryData) {
                if((l_eachCountryData.split(",")[0]).equalsIgnoreCase(l_eachCountry.getD_countryName())){
                    l_matchedCountry = l_eachCountryData;
                    break;
                }
            }
            if(l_matchedCountry.split(",").length > 4){
                for(int i = 4; i < l_matchedCountry.split(",").length; i++) {
                    Country l_country = this.getCountryByName(p_countryList, l_matchedCountry.split(",")[i]);
                    l_eachCountry.addCountryNeighbour(l_country.getD_countryID());
                }
            }
        }
        return l_updatedCountryList;
    }

    private Country getCountryByName(List<Country> p_countryList, String p_countryName) {
        for (Country l_eachCountry : p_countryList) {
            if (l_eachCountry.getD_countryName().equals(p_countryName)) {
                return l_eachCountry;
            }
        }
        return null;
    }

    private List<Country> parseCountryMetaData(List<String> p_countryData, List<Continent> p_continentList) {
        List<Country> l_countryList = new ArrayList<>();
        int l_countryId = 1;
        for (String l_eachCountry : p_countryData) {
            String[] l_countryData = l_eachCountry.split(",");
            Continent l_continent = this.getContinentByName(p_continentList, l_countryData[3]);
            Country l_country = new Country(l_countryId, l_countryData[0], l_continent.getD_continentID());
            l_countryList.add(l_country);
            l_countryId++;
        }
        return l_countryList;
    }

    private Continent getContinentByName(List<Continent> p_continentList, String p_continentName) {
        for (Continent l_eachContinent : p_continentList) {
            if (l_eachContinent.getD_continentName().equals(p_continentName)) {
                return l_eachContinent;
            }
        }
        return null;
    }

    private List<Continent> parseContinentMetaData(List<String> p_continentData) {
        int l_contientId = 1;
        List<Continent> l_continentList = new ArrayList<>();
        for (String l_eachContinent : p_continentData) {
            String[] l_continentData = l_eachContinent.split("=");
            l_continentList.add(new Continent(l_contientId, l_continentData[0], Integer.parseInt(l_continentData[1])));
            l_contientId++;
        }
        return l_continentList;
    }

    private List<String> getMetaData(List<String> p_fileLines, String p_parameter) {
        switch (p_parameter) {
            case "continent":
                List<String> l_continentLines = p_fileLines.subList(p_fileLines.indexOf("[Continents]") + 1, p_fileLines.indexOf("[Territories]") - 1);
                return l_continentLines;
            case "country":
                List<String> l_countryLines = p_fileLines.subList(p_fileLines.indexOf("[Territories]") + 1, p_fileLines.size());
                return l_countryLines;
            default:
                return null;
        }
    }
}
