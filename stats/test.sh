values="1 5 10 50 100 500 1000" 
#wiGenRunner="\basura\wigen-0.0.1\wigen-0.0.1\bin\wigen.bat"
wiGenRunner=./wigen

for value in $values
do
outName="outCountries"
params="--countries $value --dataSets 1 --slices 1 --observations 1 --computations 1 --Indicators 1 --Organizations 1 --shex --time"
paramsSingle="$params --no-scopeNodes"
paramsBad="$params --badCountries 1"
echo $params
$wiGenRunner $params >> $outName 
$wiGenRunner $paramsSingle >> $outName
$wiGenRunner $paramsBad >> $outName

outName="outDataSets"
params="--countries 1 --dataSets $value --slices 1 --observations 1 --computations 1 --Indicators 1 --Organizations 1 --shex --time"
paramsSingle="$params --no-scopeNodes"
paramsBad="$params --badCountries 1"
$wiGenRunner $params >> $outName 
$wiGenRunner $paramsSingle >> $outName
$wiGenRunner $paramsBad >> $outName

outName="outSlices"
params="--countries 1 --dataSets 1 --slices $value --observations 1 --computations 1 --Indicators 1 --Organizations 1 --shex --time"
paramsSingle="$params --no-scopeNodes"
paramsBad="$params --badCountries 1"
$wiGenRunner $params >> $outName 
$wiGenRunner $paramsSingle >> $outName
$wiGenRunner $paramsBad >> $outName

outName="outObs"
params="--countries 1 --dataSets 1 --slices 1 --observations $value --computations 1 --Indicators 1 --Organizations 1 --shex --time"
paramsSingle="$params --no-scopeNodes"
paramsBad="$params --badCountries 1"
$wiGenRunner $params >> $outName 
$wiGenRunner $paramsSingle >> $outName
$wiGenRunner $paramsBad >> $outName

outName="outComps"
params="--countries 1 --dataSets 1 --slices 1 --observations 1 --computations $value --Indicators 1 --Organizations 1 --shex --time"
paramsSingle="$params --no-scopeNodes"
paramsBad="$params --badCountries 1"
$wiGenRunner $params >> $outName 
$wiGenRunner $paramsSingle >> $outName
$wiGenRunner $paramsBad >> $outName

outName="outIndicators"
params="--countries 1 --dataSets 1 --slices 1 --observations 1 --computations 1 --Indicators $value --Organizations 1 --shex --time"
paramsSingle="$params --no-scopeNodes"
paramsBad="$params --badCountries 1"
$wiGenRunner $params >> $outName 
$wiGenRunner $paramsSingle >> $outName
$wiGenRunner $paramsBad >> $outName

outName="outOrgs"
params="--countries 1 --dataSets 1 --slices 1 --observations 1 --computations 1 --Indicators 1 --Organizations $value --shex --time"
paramsSingle="$params --no-scopeNodes"
paramsBad="$params --badCountries 1"
$wiGenRunner $params >> $outName 
$wiGenRunner $paramsSingle >> $outName
$wiGenRunner $paramsBad >> $outName

done


