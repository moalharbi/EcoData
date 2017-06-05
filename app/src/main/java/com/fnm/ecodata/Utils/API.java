package com.fnm.ecodata.Utils;

import java.net.URLEncoder;

import com.fnm.ecodata.R;

/**
 * Created by MuhammadDev on 2/7/17.
 */

// This class will be used to access to all the Urls using by this app
public class API {
	
    
	
    public static String getPlantAndAnimalMarkerInfo() {
        return "http://portal-dev.tern.org.au:8983/solr/tddp/select?q=(subject_value_resolved" + URLEncoder.encode(":") + "verte*+OR+subject_value_resolved" + URLEncoder.encode(":") + "inverte*+OR+subject_value_resolved" + URLEncoder.encode(":") + "fauna+OR+subject_value_resolved" + URLEncoder.encode(":") + "animal+OR+subject_value_resolved" + URLEncoder.encode(":") + "flora+OR+subject_value_resolved" + URLEncoder.encode(":") + "plant+OR+subject_value_resolved" + URLEncoder.encode(":") + "species+OR+subject_value_resolved" + URLEncoder.encode(":") + "biota+OR+subject_value_resolved" + URLEncoder.encode(":") + "biodiversity)&rows=999&wt=json&indent=true&fl=id,spatial_coverage_centres,description_type,description_value,slug,title,simplified_title,citation_info_search,access_rights";
    }

    public static String getPlantAndAnimalMarkerNewInfo(int start) {
        return "http://portal-dev.tern.org.au/api_tern?topic=plants_and_animal&start=" + start + "&count=100";
    }

    public static String getVegetationMarkerInfo() {
        return "http://portal-dev.tern.org.au:8983/solr/tddp/select?q=(subject_value_resolved" + URLEncoder.encode(":") + "vegetation+OR+subject_value_resolved" + URLEncoder.encode(":") + "revegetation+OR+subject_value_resolved" + URLEncoder.encode(":") + "biomass+OR+subject_value_resolved" + URLEncoder.encode(":") + "canopy+OR+subject_value_resolved" + URLEncoder.encode(":") + "reforestation+OR+subject_value_resolved" + URLEncoder.encode(":") + "carbon+OR+subject_value_resolved" + URLEncoder.encode(":") + "22tree+ring*22)&rows=999&wt=json&indent=true&fl=id,spatial_coverage_centres,description_type,description_value,slug,title,simplified_title,citation_info_search,access_rights";
    }

    public static String getVegetationMarkerNewInfo(int start) {
        return "http://portal-dev.tern.org.au/api_tern?topic=vegetation&start=" + start + "&count=100";
    }

    public static String getTerrestrialMarkerInfo() {
        return "http://portal-dev.tern.org.au:8983/solr/tddp/select?q=(subject_value_resolved" + URLEncoder.encode(":") + "terrestrial+OR+subject_value_resolved" + URLEncoder.encode(":") + "ecosystem+OR+subject_value_resolved" + URLEncoder.encode(":") + "wetlands+OR+subject_value_resolved" + URLEncoder.encode(":") + "grass*+OR+subject_value_resolved" + URLEncoder.encode(":") + "forest+OR+subject_value_resolved" + URLEncoder.encode(":") + "habitat+OR+subject_value_resolved" + URLEncoder.encode(":") + "land+OR+subject_value_resolved" + URLEncoder.encode(":") + "desert+OR+subject_value_resolved" + URLEncoder.encode(":") + "islands+OR+subject_value_resolved" + URLEncoder.encode(":") + "savannas)&wt=json&indent=true&fl=id,spatial_coverage_centres,description_type,description_value,slug,title,simplified_title,citation_info_search,access_rights&rows=999";
    }

    public static String getTerrestrialMarkerNewInfo(int start) {
        return "http://portal-dev.tern.org.au/api_tern?topic=terrestrial_ecosystems&start=" + start + "&count=100";
    }

    public static String getFreshWaterMarkerInfo() {
        return "http://portal-dev.tern.org.au:8983/solr/tddp/select?q=(subject_value_resolved" + URLEncoder.encode(":") + "*22fresh+water22*+OR+subject_value_resolved" + URLEncoder.encode(":") + "*freshwater*+OR+subject_value_resolved" + URLEncoder.encode(":") + "*estuarine*+OR+subject_value_resolved" + URLEncoder.encode(":") + "*riparian*+OR+subject_value_resolved" + URLEncoder.encode(":") + "*hydro*+OR+subject_value_resolved" + URLEncoder.encode(":") + "*22surface+water22*+OR+subject_value_resolved" + URLEncoder.encode(":") + "*22ground+water22*+OR+subject_value_resolved" + URLEncoder.encode(":") + "*22water+quality22*+OR+subject_value_resolved" + URLEncoder.encode(":") + "*creek*+OR+subject_value_resolved" + URLEncoder.encode(":") + "river*+OR+subject_value_resolved" + URLEncoder.encode(":") + "stream*+OR+subject_value_resolved" + URLEncoder.encode(":") + "lake*+OR+subject_value_resolved" + URLEncoder.encode(":") + "flood*+OR+subject_value_resolved" + URLEncoder.encode(":") + "flow*+OR+subject_value_resolved" + URLEncoder.encode(":") + "glacier*+OR+subject_value_resolved" + URLEncoder.encode(":") + "snow+OR+subject_value_resolved" + URLEncoder.encode(":") + "ice)&rows=999&wt=json&indent=true&fl=id,spatial_coverage_centres,description_type,description_value,slug,title,simplified_title,citation_info_search,access_rights";
    }

    public static String getFreshWaterMarkerNewInfo(int start) {
        return "http://portal-dev.tern.org.au/api_tern?topic=fresh_water_and_estuarine&start=" + start + "&count=100";
    }

    public static String getAgricultureMarkerInfo() {
        return "http://portal-dev.tern.org.au:8983/solr/tddp/select?q=(subject_value_resolved" + URLEncoder.encode(":") + "*agricultur*+OR+subject_value_resolved" + URLEncoder.encode(":") + "*farm*+OR+subject_value_resolved" + URLEncoder.encode(":") + "*pastoral*+OR+subject_value_resolved" + URLEncoder.encode(":") + "*pastur*+OR+subject_value_resolved" + URLEncoder.encode(":") + "*graz*+OR+subject_value_resolved" + URLEncoder.encode(":") + "crop*+OR+subject_value_resolved" + URLEncoder.encode(":") + "*irrigation*+OR+subject_value_resolved" + URLEncoder.encode(":") + "*conserv*+OR+subject_value_resolved" + URLEncoder.encode(":") + "*manage*)&wt=json&indent=true&rows=999&fl=id,spatial_coverage_centres,description_type,description_value,slug,title,simplified_title,citation_info_search,access_rights";
    }

    public static String getAgricultureMarkerNewInfo(int start) {
        return "http://portal-dev.tern.org.au/api_tern?topic=agriculture&start=" + start + "&count=100";
    }

    public static String getLandsurfaceAndSoilMarkerInfo() {
        return "http://portal-dev.tern.org.au:8983/solr/tddp/select?q=(subject_value_resolved" + URLEncoder.encode(":") + "*22land+surface22*+OR+subject_value_resolved" + URLEncoder.encode(":") + "*soil*+OR+subject_value_resolved" + URLEncoder.encode(":") + "erosion+OR+subject_value_resolved" + URLEncoder.encode(":") + "sedimen*+OR+subject_value_resolved" + URLEncoder.encode(":") + "land*+OR+subject_value_resolved" + URLEncoder.encode(":") + "topography+OR+subject_value_resolved" + URLEncoder.encode(":") + "22frozen+OR+subject_value_resolved" + URLEncoder.encode(":") + "land22+OR+subject_value_resolved" + URLEncoder.encode(":") + "22soil+OR+subject_value_resolved" + URLEncoder.encode(":") + "salinity22)&rows=999&wt=json&indent=true&fl=id,spatial_coverage_centres,description_type,description_value,slug,title,simplified_title,citation_info_search,access_rights";
    }

    public static String getLandsurfaceAndSoilMarkerNewInfo(int start) {
        return "http://portal-dev.tern.org.au/api_tern?topic=land_surface_and_soils&start=" + start + "&count=100";
    }

    public static String getOceanCostMarkerInfo() {
        return "http://portal-dev.tern.org.au:8983/solr/tddp/select?q=(subject_value_resolved" + URLEncoder.encode(":") + "*coast*+OR+subject_value_resolved" + URLEncoder.encode(":") + "*ocean*+OR+subject_value_resolved" + URLEncoder.encode(":") + "marine+OR+subject_value_resolved" + URLEncoder.encode(":") + "beach+OR+subject_value_resolved" + URLEncoder.encode(":") + "bathmetry+OR+subject_value_resolved" + URLEncoder.encode(":") + "tide*+OR+subject_value_resolved" + URLEncoder.encode(":") + "aqua*+OR+subject_value_resolved" + URLEncoder.encode(":") + "sea*+OR+subject_value_resolved" + URLEncoder.encode(":") + "saltwa*+OR+subject_value_resolved" + URLEncoder.encode(":") + "storm+OR+subject_value_resolved" + URLEncoder.encode(":") + "shoreline+OR+subject_value_resolved" + URLEncoder.encode(":") + "wave*+OR+subject_value_resolved" + URLEncoder.encode(":") + "salinity)&wt=json&indent=true&rows=999&fl=id,spatial_coverage_centres,description_type,description_value,slug,title,simplified_title,citation_info_search,access_rights";
    }

    public static String getOceanCostMarkerNewInfo(int start) {
        return "http://portal-dev.tern.org.au/api_tern?topic=oceans_and_coasts&start=" + start + "&count=100";
    }

    public static String getClimateMarkerInfo() {
        return "http://portal-dev.tern.org.au:8983/solr/tddp/select?q=(subject_value_resolved" + URLEncoder.encode(":") + "*climate*+OR+subject_value_resolved" + URLEncoder.encode(":") + "index*+OR+subject_value_resolved" + URLEncoder.encode(":") + "*indice*+OR+subject_value_resolved" + URLEncoder.encode(":") + "*anomaly*+OR+subject_value_resolved" + URLEncoder.encode(":") + "*oscillation*+OR+subject_value_resolved" + URLEncoder.encode(":") + "*pattern*)&wt=json&indent=true&rows=999&fl=id,spatial_coverage_centres,description_type,description_value,slug,title,simplified_title,citation_info_search,access_rights";
    }

    public static String getClimateMarkerNewInfo(int start) {
        return "http://portal-dev.tern.org.au/api_tern?topic=climate&start=" + start + "&count=100";
    }

    public static String getHumanNatureMarkerInfo() {
        return "http://portal-dev.tern.org.au:8983/solr/tddp/select?q=(subject_value_resolved" + URLEncoder.encode(":") + "*human*+OR+subject_value_resolved" + URLEncoder.encode(":") + "22human+impact22+OR+subject_value_resolved" + URLEncoder.encode(":") + "survey+OR+subject_value_resolved" + URLEncoder.encode(":") + "boundaries+OR+subject_value_resolved" + URLEncoder.encode(":") + "economi*+OR+subject_value_resolved" + URLEncoder.encode(":") + "productio*+OR+subject_value_resolved" + URLEncoder.encode(":") + "behav*+OR+subject_value_resolved" + URLEncoder.encode(":") + "infrastructure+OR+subject_value_resolved" + URLEncoder.encode(":") + "22land+management22+OR+subject_value_resolved" + URLEncoder.encode(":") + "harzard*)&wt=json&indent=true&rows=999&fl=id,spatial_coverage_centres,description_type,description_value,slug,title,simplified_title,citation_info_search,access_rights";
    }

    public static String getHumanNatureMarkerNewInfo(int start) {
        return "http://portal-dev.tern.org.au/api_tern?topic=human_nature_interactions&start=" + start + "&count=100";
    }

    public static String getEnergyWaterGasMarkerInfo() {
        return "http://portal-dev.tern.org.au:8983/solr/tddp/select?q=(subject_value_resolved" + URLEncoder.encode(":") + "*energy*+OR+subject_value_resolved" + URLEncoder.encode(":") + "gas*+OR+subject_value_resolved" + URLEncoder.encode(":") + "flux*+OR+subject_value_resolved" + URLEncoder.encode(":") + "atmospher*+OR+subject_value_resolved" + URLEncoder.encode(":") + "cloud*+OR+subject_value_resolved" + URLEncoder.encode(":") + "air*+OR+subject_value_resolved" + URLEncoder.encode(":") + "Pheno*+OR+subject_value_resolved" + URLEncoder.encode(":") + "radia*+OR+subject_value_resolved" + URLEncoder.encode(":") + "vapo*+OR+subject_value_resolved" + URLEncoder.encode(":") + "wind*+OR+subject_value_resolved" + URLEncoder.encode(":") + "precipitation*+OR+subject_value_resolved" + URLEncoder.encode(":") + "rain*)&wt=json&indent=true&rows=999&fl=id,spatial_coverage_centres,description_type,description_value,slug,title,simplified_title,citation_info_search,access_rights";
    }

    public static String getEnergyWaterGasMarkerNewInfo(int start) {
        return "http://portal-dev.tern.org.au/api_tern?topic=energy_water_and_gas_exchange&start=" + start + "&count=100";
    }

    public static String urlSearchByText(String query) {
        return "http://portal-dev.tern.org.au:8983/solr/tddp/select?q=*" + URLEncoder.encode(":") + "*&fq=(subject_value_resolved" + URLEncoder.encode(":") + "*" + query + "*" + URLEncoder.encode(" OR ") + "description_value" + URLEncoder.encode(":") + "*" + query + "*)&rows=500&wt=json&indent=true&fl=id,spatial_coverage_centres,description_type,description_value,slug,title,simplified_title,citation_info_search,access_rights";
    }

    public static String advanceSearch(String preUrl, String query) {
        return preUrl + "&fq=(subject_value_resolved" + URLEncoder.encode(":") + "*" + query + "*" + URLEncoder.encode(" OR ") + "description_value" + URLEncoder.encode(":") + "*" + query + "*)";
    }

    public static String urlSearchByLocation() {
        return "http://portal-dev.tern.org.au:8983/solr/tddp/select?q=*" + URLEncoder.encode(":") + "*&wt=json&indent=true&fl=id,spatial_coverage_centres,description_type,description_value,slug,title,simplified_title,citation_info_search,access_rights&rows=9999";
    }

    public static String getStatesUrl(int start, String stateCode) {
        String url = "http://portal-dev.tern.org.au/api_tern?start=" + start + "&count=100";
        if (stateCode.equals("New South Wales")) {
            // New South Wales = nsw
            url += "&state=nsw";
        } else if (stateCode.equals("Queensland")) {
            // Queensland = qld,queensland
            url += "&state=qld";
        } else if (stateCode.equals("Victoria")) {
            // Victoria vic, victoria
            url += "&state=vic";
        } else if (stateCode.equals("South Australia")) {
            // South Australia sa
            url += "&state=sa";
        } else if (stateCode.equals("Western Australia")) {
            // Western Australia wa
            url += "&state=wa";
        } else if (stateCode.equals("Northern Territory")) {
            // Nothern Territory nt
            url += "&state=nt";
        } else if (stateCode.equals("Australian Capital Territory")) {
            // Australian Capital Territory act
            url += "&state=act";
        } else if (stateCode.equals("Tasmania")) {
            // Tasmania tas, tasmania
            url += "&state=tas";
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
