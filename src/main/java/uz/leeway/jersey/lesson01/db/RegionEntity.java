package uz.leeway.jersey.lesson01.db;

public class RegionEntity {

    private Integer region_id;
    private String name;
    private Integer soni;

    public Integer getSoni() {
        return soni;
    }

    public void setSoni(Integer soni) {
        this.soni = soni;
    }

    public Integer getRegion_id() {
        return region_id;
    }

    public void setRegion_id(Integer region_id) {
        this.region_id = region_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
