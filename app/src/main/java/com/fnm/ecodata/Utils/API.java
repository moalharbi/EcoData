package com.fnm.ecodata.Utils;

import com.fnm.ecodata.R;

/**
 * Created by MuhammadDev on 2/7/17.
 */

// This class will be used to access to all the Urls using by this app
public class API {
    public static String getPlantAndAnimalMarkerInfo() {
        return "http://portal-dev.tern.org.au:8983/solr/tddp/select?q=(subject_value_resolved%3Averte*+OR+subject_value_resolved%3Ainverte*+OR+subject_value_resolved%3Afauna+OR+subject_value_resolved%3Aanimal+OR+subject_value_resolved%3Aflora+OR+subject_value_resolved%3Aplant+OR+subject_value_resolved%3Aspecies+OR+subject_value_resolved%3Abiota+OR+subject_value_resolved%3Abiodiversity)&rows=999&wt=json&indent=true&fl=id,spatial_coverage_centres,description_type,description_value,slug,title,simplified_title,citation_info_search";
    }

    public static String getPlantAndAnimalMarkerNewInfo(int start) {
        return "http://portal-dev.tern.org.au/api_tern?topic=plants_and_animal&start="+start+"&count=100";
    }


    public static String getVegetationMarkerInfo() {
        return "http://portal-dev.tern.org.au:8983/solr/tddp/select?q=(subject_value_resolved%3Avegetation+OR+subject_value_resolved%3Arevegetation+OR+subject_value_resolved%3Abiomass+OR+subject_value_resolved%3Acanopy+OR+subject_value_resolved%3Areforestation+OR+subject_value_resolved%3Acarbon+OR+subject_value_resolved%3A22tree+ring*22)&rows=999&wt=json&indent=true&fl=id,spatial_coverage_centres,description_type,description_value,slug,title,simplified_title,citation_info_search";
    }

    public static String getVegetationMarkerNewInfo(int start) {
        return "http://portal-dev.tern.org.au/api_tern?topic=vegetation&start="+start+"&count=100";
    }

    public static String getTerrestrialMarkerInfo() {
        return "http://portal-dev.tern.org.au:8983/solr/tddp/select?q=(subject_value_resolved%3Aterrestrial+OR+subject_value_resolved%3Aecosystem+OR+subject_value_resolved%3Awetlands+OR+subject_value_resolved%3Agrass*+OR+subject_value_resolved%3Aforest+OR+subject_value_resolved%3Ahabitat+OR+subject_value_resolved%3Aland+OR+subject_value_resolved%3Adesert+OR+subject_value_resolved%3Aislands+OR+subject_value_resolved%3Asavannas)&wt=json&indent=true&fl=id,spatial_coverage_centres,description_type,description_value,slug,title,simplified_title,citation_info_search&rows=999";
    }


    public static String getTerrestrialMarkerNewInfo(int start) {
        return "http://portal-dev.tern.org.au/api_tern?topic=terrestrial_ecosystems&start="+start+"&count=100";
    }

    public static String getFreshWaterMarkerInfo() {
        return "http://portal-dev.tern.org.au:8983/solr/tddp/select?q=(subject_value_resolved%3A*22fresh+water22*+OR+subject_value_resolved%3A*freshwater*+OR+subject_value_resolved%3A*estuarine*+OR+subject_value_resolved%3A*riparian*+OR+subject_value_resolved%3A*hydro*+OR+subject_value_resolved%3A*22surface+water22*+OR+subject_value_resolved%3A*22ground+water22*+OR+subject_value_resolved%3A*22water+quality22*+OR+subject_value_resolved%3A*creek*+OR+subject_value_resolved%3Ariver*+OR+subject_value_resolved%3Astream*+OR+subject_value_resolved%3Alake*+OR+subject_value_resolved%3Aflood*+OR+subject_value_resolved%3Aflow*+OR+subject_value_resolved%3Aglacier*+OR+subject_value_resolved%3Asnow+OR+subject_value_resolved%3Aice)&rows=999&wt=json&indent=true&fl=id,spatial_coverage_centres,description_type,description_value,slug,title,simplified_title,citation_info_search";
    }

    public static String getFreshWaterMarkerNewInfo(int start) {
        return "http://portal-dev.tern.org.au/api_tern?topic=fresh_water_and_estuarine&start="+start+"&count=100";
    }

    public static String getAgricultureMarkerInfo() {
        return "http://portal-dev.tern.org.au:8983/solr/tddp/select?q=%0A(subject_value_resolved%3A*agricultur*+OR+subject_value_resolved%3A*farm*+OR+subject_value_resolved%3A*pastoral*+OR+subject_value_resolved%3A*pastur*+OR+subject_value_resolved%3A*graz*+OR+subject_value_resolved%3Acrop*+OR+subject_value_resolved%3A*irrigation*+OR+subject_value_resolved%3A*conserv*+OR+subject_value_resolved%3A*manage*)%0A&wt=json&indent=true&rows=999&fl=id,spatial_coverage_centres,description_type,description_value,slug,title,simplified_title,citation_info_search";
    }

    public static String getAgricultureMarkerNewInfo(int start) {
        return "http://portal-dev.tern.org.au/api_tern?topic=agriculture&start="+start+"&count=100";
    }

    public static String getLandsurfaceAndSoilMarkerInfo() {
        return "http://portal-dev.tern.org.au:8983/solr/tddp/select?q=(subject_value_resolved%3A*22land+surface22*+OR+subject_value_resolved%3A*soil*+OR+subject_value_resolved%3Aerosion+OR+subject_value_resolved%3Asedimen*+OR+subject_value_resolved%3Aland*+OR+subject_value_resolved%3Atopography+OR+subject_value_resolved%3A22frozen+OR+subject_value_resolved%3Aland22+OR+subject_value_resolved%3A22soil+OR+subject_value_resolved%3Asalinity22)&rows=999&wt=json&indent=true&fl=id,spatial_coverage_centres,description_type,description_value,slug,title,simplified_title,citation_info_search";
    }

    public static String getLandsurfaceAndSoilMarkerNewInfo(int start) {
        return "http://portal-dev.tern.org.au/api_tern?topic=land_surface_and_soils&start="+start+"&count=100";
    }



    public static String getOceanCostMarkerInfo() {
        return "http://portal-dev.tern.org.au:8983/solr/tddp/select?q=(subject_value_resolved%3A*coast*+OR+subject_value_resolved%3A*ocean*+OR+subject_value_resolved%3Amarine+OR+subject_value_resolved%3Abeach+OR+subject_value_resolved%3Abathmetry+OR+subject_value_resolved%3Atide*+OR+subject_value_resolved%3Aaqua*+OR+subject_value_resolved%3Asea*+OR+subject_value_resolved%3Asaltwa*+OR+subject_value_resolved%3Astorm+OR+subject_value_resolved%3Ashoreline+OR+subject_value_resolved%3Awave*+OR+subject_value_resolved%3Asalinity)&wt=json&indent=true&rows=999&fl=id,spatial_coverage_centres,description_type,description_value,slug,title,simplified_title,citation_info_search";
    }

    public static String getOceanCostMarkerNewInfo(int start) {
        return "http://portal-dev.tern.org.au/api_tern?topic=oceans_and_coasts&start="+start+"&count=100";
    }

    public static String getClimateMarkerInfo() {
        return "http://portal-dev.tern.org.au:8983/solr/tddp/select?q=(subject_value_resolved%3A*climate*+OR+subject_value_resolved%3Aindex*+OR+subject_value_resolved%3A*indice*+OR+subject_value_resolved%3A*anomaly*+OR+subject_value_resolved%3A*oscillation*+OR+subject_value_resolved%3A*pattern*)&wt=json&indent=true&rows=999&fl=id,spatial_coverage_centres,description_type,description_value,slug,title,simplified_title,citation_info_search";
    }

    public static String getClimateMarkerNewInfo(int start) {
        return "http://portal-dev.tern.org.au/api_tern?topic=climate&start="+start+"&count=100";
    }

    public static String getHumanNatureMarkerInfo() {
        return "http://portal-dev.tern.org.au:8983/solr/tddp/select?q=(subject_value_resolved%3A*human*+OR+subject_value_resolved%3A22human+impact22+OR+subject_value_resolved%3Asurvey+OR+subject_value_resolved%3Aboundaries+OR+subject_value_resolved%3Aeconomi*+OR+subject_value_resolved%3Aproductio*+OR+subject_value_resolved%3Abehav*+OR+subject_value_resolved%3Ainfrastructure+OR+subject_value_resolved%3A22land+management22+OR+subject_value_resolved%3Aharzard*)&wt=json&indent=true&rows=999&fl=id,spatial_coverage_centres,description_type,description_value,slug,title,simplified_title,citation_info_search";
    }

    public static String getHumanNatureMarkerNewInfo(int start) {
        return "http://portal-dev.tern.org.au/api_tern?topic=human_nature_interactions&start="+start+"&count=100";
    }

    public static String getEnergyWaterGasMarkerInfo() {
        return "http://portal-dev.tern.org.au:8983/solr/tddp/select?q=(subject_value_resolved%3A*energy*+OR+subject_value_resolved%3Agas*+OR+subject_value_resolved%3Aflux*+OR+subject_value_resolved%3Aatmospher*+OR+subject_value_resolved%3Acloud*+OR+subject_value_resolved%3Aair*+OR+subject_value_resolved%3APheno*+OR+subject_value_resolved%3Aradia*+OR+subject_value_resolved%3Avapo*+OR+subject_value_resolved%3Awind*+OR+subject_value_resolved%3Aprecipitation*+OR+subject_value_resolved%3Arain*)&wt=json&indent=true&rows=999&fl=id,spatial_coverage_centres,description_type,description_value,slug,title,simplified_title,citation_info_search";
    }


    public static String getEnergyWaterGasMarkerNewInfo(int start) {
        return "http://portal-dev.tern.org.au/api_tern?topic=energy_water_and_gas_exchange&start="+start+"&count=100";
    }

    public static String urlSearchByText(String query) {
        return "http://portal-dev.tern.org.au:8983/solr/tddp/select?q=*%3A*&fq=(subject_value_resolved%3A*"+ query +"*%20OR%20description_value%3A*"+query+"*)&rows=500&wt=json&indent=true&fl=id,spatial_coverage_centres,description_type,description_value,slug,title,simplified_title";
    }

    public static String advanceSearch(String preUrl, String query){
        return preUrl+"&fq=(subject_value_resolved%3A*"+ query +"*%20OR%20description_value%3A*"+query+"*)";
    }

    public static String urlSearchByLocation() {
        return "http://portal-dev.tern.org.au:8983/solr/tddp/select?q=*%3A*&wt=json&indent=true&fl=id,spatial_coverage_centres,description_type,description_value,slug,title,simplified_title,citation_info_search&rows=9999";
    }

    public static String getStatesUrl(int start, String stateCode){
        String url = "http://portal-dev.tern.org.au/api_tern?start="+start+"&count=100";
        if(stateCode.equals("New South Wales")) {
            // New South Wales = nsw
            url+="&state=nsw";
        }else if(stateCode.equals("Queensland")) {
            // Queensland = qld,queensland
            url+="&state=qld";
        }else if(stateCode.equals("Victoria")) {
            // Victoria vic, victoria
            url+="&state=vic";
        }else if(stateCode.equals("South Australia")) {
            // South Australia sa
            url+="&state=sa";
        }else if(stateCode.equals("Western Australia")) {
            // Western Australia wa
            url+="&state=wa";
        }else if(stateCode.equals("Northern Territory")) {
            // Nothern Territory nt
            url+="&state=nt";
        }else if(stateCode.equals("Australian Capital Territory")) {
            // Australian Capital Territory act
            url+="&state=act";
        }else if(stateCode.equals("Tasmania")) {
            // Tasmania tas, tasmania
            url+="&state=tas";
        }
        return url;
    }

    public static String getPagingURLForCategory(String category, String query, int count) {
        String url = null;
        switch (category) {
            case "Plants And Animals":
                url = API.getPlantAndAnimalMarkerNewInfo(count);
                break;

            case "Vegetation":
                url = API.getVegetationMarkerNewInfo(count);
                break;

            case "Terrestrial Eco System":
                url = API.getTerrestrialMarkerNewInfo(count);
                break;

            case "Fresh Water And Estuarine":
                url = API.getFreshWaterMarkerNewInfo(count);
                break;

            case "Land Surface And Soil":
                url = API.getLandsurfaceAndSoilMarkerNewInfo(count);
                break;

            case "Agriculture":
                url = API.getAgricultureMarkerNewInfo(count);
                break;

            case "Ocean And Coasts":
                url = API.getOceanCostMarkerNewInfo(count);
                break;

            case "Climate":
                url = API.getClimateMarkerNewInfo(count);
                break;

            case "Human - Nature Interaction":
                url = API.getHumanNatureMarkerNewInfo(count);
                break;

            case "Energy, Water And Gas":
                url = API.getEnergyWaterGasMarkerNewInfo(count);
                break;

            case "searchByState":
                url = getStatesUrl(count, query);
                break;
        }

        return url;
    }


}
