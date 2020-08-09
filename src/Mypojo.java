public class Mypojo
{
    private String poAmount;

    private String poAccount;

    public String getPoAmount ()
    {
        return poAmount;
    }

    public void setPoAmount (String poAmount)
    {
        this.poAmount = poAmount;
    }

    public String getPoAccount ()
    {
        return poAccount;
    }

    public void setPoAccount (String poAccount)
    {
        this.poAccount = poAccount;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [poAmount = "+poAmount+", poAccount = "+poAccount+"]";
    }
}