CREATE OR REPLACE VIEW public.cs3_obfuscated
 AS
 SELECT 
    m.ogc_fid,
    ARRAY_AGG(d.description) as descriptions,
    m.wkb_geometry
   FROM cs3_data d,
    crete_municipalities m
  WHERE st_contains(m.wkb_geometry, d.coords)
  GROUP BY m.ogc_fid;
  
CREATE OR REPLACE FUNCTION ST_CS3ObfuscatedToGeoJson()
RETURNS jsonb AS
$BODY$
    SELECT jsonb_build_object(
        'type',     'FeatureCollection',
        'features', jsonb_agg(feature)
    )
    FROM (
      SELECT jsonb_build_object(
        'type',       'Feature',
        'id',         row.ogc_fid,
        'geometry',   ST_AsGeoJSON(wkb_geometry)::jsonb,
        'properties', to_jsonb(row) - 'ogc_fid' - 'wkb_geometry'
      ) AS feature
      FROM (SELECT * FROM public.cs3_obfuscated) row) features;
$BODY$
LANGUAGE SQL;