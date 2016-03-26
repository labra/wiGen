values="1 2"
wiGenRunner="\basura\wigen-0.0.1\wigen-0.0.1\bin\wigen.bat"

countries="1"
dataSets="1"
slices="1"
obs="1"
comps="1"
indicators="1"
orgs="1"

for countries in $values
do
 $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time --scopeNodes >> out 
# $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shacl --time --scopeNodes >> out 
 $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time --no-scopeNodes >> out 
# $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shacl --time --no-scopeNodes >> out 
 $wiGenRunner -c $countries --badCountries 1 -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time --scopeNodes >> out 
# $wiGenRunner -c $countries --badCountries 1 -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shacl --time --scopeNodes >> out 
done

for dataSets in $values
do
 $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time --scopeNodes >> out 
# $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shacl --time --scopeNodes >> out 
 $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time --no-scopeNodes >> out 
# $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shacl --time --no-scopeNodes >> out 
 $wiGenRunner -c $countries --badCountries 1 -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time --scopeNodes >> out 
# $wiGenRunner -c $countries --badCountries 1 -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shacl --time --scopeNodes >> out 
done

for slices in $values
do
 $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time --scopeNodes >> out 
# $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shacl --time --scopeNodes >> out 
 $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time --no-scopeNodes >> out 
# $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shacl --time --no-scopeNodes >> out 
 $wiGenRunner -c $countries --badCountries 1 -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time --scopeNodes >> out 
# $wiGenRunner -c $countries --badCountries 1 -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shacl --time --scopeNodes >> out 
done

for obs in $values
do
 $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time --scopeNodes >> out 
# $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shacl --time --scopeNodes >> out 
 $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time --no-scopeNodes >> out 
# $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shacl --time --no-scopeNodes >> out 
 $wiGenRunner -c $countries --badCountries 1 -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time --scopeNodes >> out 
# $wiGenRunner -c $countries --badCountries 1 -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shacl --time --scopeNodes >> out 
done

for comps in $values
do
 $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time --scopeNodes >> out 
# $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shacl --time --scopeNodes >> out 
 $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time --no-scopeNodes >> out 
# $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shacl --time --no-scopeNodes >> out 
 $wiGenRunner -c $countries --badCountries 1 -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time --scopeNodes >> out 
# $wiGenRunner -c $countries --badCountries 1 -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shacl --time --scopeNodes >> out 
done

for indicators in $values
do
 $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time --scopeNodes >> out 
# $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shacl --time --scopeNodes >> out 
 $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time --no-scopeNodes >> out 
# $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shacl --time --no-scopeNodes >> out 
 $wiGenRunner -c $countries --badCountries 1 -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time --scopeNodes >> out 
# $wiGenRunner -c $countries --badCountries 1 -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shacl --time --scopeNodes >> out 
done

for orgs in $values
do
 $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time --scopeNodes >> out 
# $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shacl --time --scopeNodes >> out 
 $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time --no-scopeNodes >> out 
# $wiGenRunner -c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shacl --time --no-scopeNodes >> out 
 $wiGenRunner -c $countries --badCountries 1 -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time --scopeNodes >> out 
# $wiGenRunner -c $countries --badCountries 1 -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shacl --time --scopeNodes >> out 
done