values="1 2" 
# values="1 5 10 50 100 500 1000" 
wiGenRunner="\basura\wigen-0.0.1\wigen-0.0.1\bin\wigen.bat"
# wiGenRunner=./wiGen

countries="1"
dataSets="1"
slices="1"
obs="1"
comps="1"
indicators="1"
orgs="1"

for countries in $values
do
outName="outCountries"
params="-c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time"
paramsSingle="$params --no-scopeNodes"
paramsBad="$params --badCountries 1"
$wiGenRunner $params >> $outName 
$wiGenRunner $paramsSingle >> $outName
$wiGenRunner $paramsBad >> $outName
done

for dataSets in $values
do
outName="outDataSets"
params="-c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time"
paramsSingle="$params --no-scopeNodes"
paramsBad="$params --badCountries 1"
$wiGenRunner $params >> $outName 
$wiGenRunner $paramsSingle >> $outName
$wiGenRunner $paramsBad >> $outName
done

for slices in $values
do
 outName="outSlices"
 params="-c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time"
 paramsSingle="$params --no-scopeNodes"
 paramsBad="$params --badCountries 1"
 $wiGenRunner $params >> $outName 
 $wiGenRunner $paramsSingle >> $outName
 $wiGenRunner $paramsBad >> $outName
done

for obs in $values
do
 outName="outObs"
 params="-c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time"
 paramsSingle="$params --no-scopeNodes"
 paramsBad="$params --badCountries 1"
 $wiGenRunner $params >> $outName 
 $wiGenRunner $paramsSingle >> $outName
 $wiGenRunner $paramsBad >> $outName
done

for comps in $values
do
 outName="outComps"
 params="-c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time"
 paramsSingle="$params --no-scopeNodes"
 paramsBad="$params --badCountries 1"
 $wiGenRunner $params >> $outName 
 $wiGenRunner $paramsSingle >> $outName
 $wiGenRunner $paramsBad >> $outName
done

for indicators in $values
do
 outName="outIndicators"
 params="-c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time"
 paramsSingle="$params --no-scopeNodes"
 paramsBad="$params --badCountries 1"
 $wiGenRunner $params >> $outName 
 $wiGenRunner $paramsSingle >> $outName
 $wiGenRunner $paramsBad >> $outName
done

for orgs in $values
do
 outName="outOrgs"
 params="-c $countries -d $dataSets --slices $slices -o $obs -p $comps -i $indicators -g $orgs --shex --time"
 paramsSingle="$params --no-scopeNodes"
 paramsBad="$params --badCountries 1"
 $wiGenRunner $params >> $outName 
 $wiGenRunner $paramsSingle >> $outName
 $wiGenRunner $paramsBad >> $outName
done