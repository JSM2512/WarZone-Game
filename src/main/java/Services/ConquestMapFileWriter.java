package Services;

import Models.Continent;
import Models.Country;
import Models.CurrentState;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

/**
 * The type Conquest map file writer.
 */
public class ConquestMapFileWriter implements Serializable {

    /**
     * Instantiates a new Conquest map file writer.
     */
    public ConquestMapFileWriter() {
    }

    /**
     * Parse map to file.
     *
     * @param p_currentState the p current state
     * @param p_writer       the p writer
     * @param p_mapFormat    the p map format
     * @throws IOException the io exception
     */
    public void parseMapToFile(CurrentState p_currentState, FileWriter p_writer, String p_mapFormat) throws IOException {
        if(p_currentState.getD_map().getD_mapContinents() != null && !p_currentState.getD_map().getD_mapContinents().isEmpty()){
            writeContinentMetaData(p_currentState,p_writer);
        }
        if(p_currentState.getD_map().getD_mapCountries() != null && !p_currentState.getD_map().getD_mapCountries().isEmpty()){
            writeCountryAndBorderMetaData(p_currentState,p_writer);
        }
    }

    /**
     * Write country and border meta data.
     *
     * @param p_currentState the p current state
     * @param p_writer       the p writer
     * @throws IOException the io exception
     */
    private void writeCountryAndBorderMetaData(CurrentState p_currentState, FileWriter p_writer) throws IOException {
        String l_countryMetadata = "";
        p_writer.write(System.lineSeparator() + "[Territories]" + System.lineSeparator());
        for(Country l_eachCountry :p_currentState.getD_map().getD_mapCountries()){
            l_countryMetadata = "";
            l_countryMetadata = l_eachCountry.getD_countryName().concat(",color1,color2,").concat(p_currentState.getD_map().getContinentById(l_eachCountry.getD_continentID()).getD_continentName());

            if(l_eachCountry.getD_neighbouringCountriesId() != null && !l_eachCountry.getD_neighbouringCountriesId().isEmpty()){
                for(Integer l_eachBorder : l_eachCountry.getD_neighbouringCountriesId()){
                    l_countryMetadata = l_countryMetadata.concat(",").concat(p_currentState.getD_map().getCountryById(l_eachBorder).getD_countryName());
                }
            }

            p_writer.write(l_countryMetadata + System.lineSeparator());
        }
    }

    /**
     * Write continent meta data.
     *
     * @param p_currentState the p current state
     * @param p_writer       the p writer
     * @throws IOException the io exception
     */
    private void writeContinentMetaData(CurrentState p_currentState, FileWriter p_writer) throws IOException {
        p_writer.write(System.lineSeparator() + "[Continents]" + System.lineSeparator());
        for(Continent l_eachContinent :p_currentState.getD_map().getD_mapContinents()){
            p_writer.write(l_eachContinent.getD_continentName() + "=" + l_eachContinent.getD_continentValue().toString() + System.lineSeparator());
        }
    }
}
