CREATE OR REPLACE FUNCTION ST_CS3DataToGeoJson()
RETURNS jsonb AS
$BODY$
    SELECT jsonb_build_object(
        'type',     'FeatureCollection',
        'features', jsonb_agg(feature)
    )
    FROM (
      SELECT jsonb_build_object(
        'type',       'Feature',
        'id',         row.id,
        'geometry',   ST_AsGeoJSON(coords)::jsonb,
        'properties', to_jsonb(row) - 'id' - 'coords'
      ) AS feature
      FROM (SELECT * FROM public.cs3_data) row) features;
$BODY$
LANGUAGE SQL;