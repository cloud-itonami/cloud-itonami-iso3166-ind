(ns culture.facts
  "Country-level regional-culture catalog for India (IND) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"IND"
   [{:culture/id "ind.dish.biryani"
     :culture/name "Biryani"
     :culture/country "IND"
     :culture/kind :dish
     :culture/summary "Mixed rice dish of rice with meat, seafood or vegetables and spices, originating from South Asia; whether the modern dish was created in Mughal-era India or derives from Persian pilau is debated."
     :culture/url "https://en.wikipedia.org/wiki/Biryani"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ind.dish.masala-dosa"
     :culture/name "Masala dosa"
     :culture/country "IND"
     :culture/kind :dish
     :culture/summary "Dish of South India consisting of a savoury dosa crepe stuffed with a spiced potato stir fry, popular as a breakfast item."
     :culture/url "https://en.wikipedia.org/wiki/Masala_dosa"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ind.dish.butter-chicken"
     :culture/name "Butter chicken"
     :culture/name-local "Murgh makhani"
     :culture/country "IND"
     :culture/kind :dish
     :culture/summary "Curry of chicken in a spiced tomato-and-butter gravy, invented by Indian chefs Kundan Lal Gujral and Kundan Lal Jaggi and popularized at the Moti Mahal restaurant in Delhi in the 1950s."
     :culture/url "https://en.wikipedia.org/wiki/Butter_chicken"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ind.beverage.masala-chai"
     :culture/name "Masala chai"
     :culture/country "IND"
     :culture/kind :beverage
     :culture/summary "Popular Indian beverage of black tea brewed in milk and water with aromatic spices such as cardamom, ginger and cinnamon, sweetened with sugar."
     :culture/url "https://en.wikipedia.org/wiki/Masala_chai"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ind.beverage.lassi"
     :culture/name "Lassi"
     :culture/country "IND"
     :culture/kind :beverage
     :culture/summary "Yogurt-based beverage with a smoothie-like consistency, originating from Punjab in the Indian subcontinent."
     :culture/url "https://en.wikipedia.org/wiki/Lassi"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ind.product.darjeeling-tea"
     :culture/name "Darjeeling tea"
     :culture/country "IND"
     :culture/kind :product
     :culture/summary "Black tea from the Darjeeling and Kalimpong districts of West Bengal, India, noted for its muscatel flavor; a registered geographical indication since 2004 with EU protected geographical indication status since 2011."
     :culture/url "https://en.wikipedia.org/wiki/Darjeeling_tea"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ind.craft.banarasi-sari"
     :culture/name "Banarasi sari"
     :culture/country "IND"
     :culture/kind :craft
     :culture/summary "Handwoven silk textile from Varanasi, India, distinguished by intricate gold and silver brocade; weaver associations secured geographical indication rights for Banaras brocades and saris in 2009."
     :culture/url "https://en.wikipedia.org/wiki/Banarasi_sari"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ind.festival.diwali"
     :culture/name "Diwali"
     :culture/country "IND"
     :culture/kind :festival
     :culture/summary "Hindu festival of lights symbolising the victory of light over darkness, celebrated primarily in India and also in Jain, Sikh and some Buddhist traditions."
     :culture/url "https://en.wikipedia.org/wiki/Diwali"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ind.festival.kumbh-mela"
     :culture/name "Kumbh Mela"
     :culture/country "IND"
     :culture/kind :festival
     :culture/summary "Hindu pilgrimage celebrated every four to twelve years at four sacred river sites in India, drawing millions of devotees for ritual bathing; inscribed as UNESCO intangible cultural heritage in 2017."
     :culture/url "https://en.wikipedia.org/wiki/Kumbh_Mela"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "ind.heritage.taj-mahal"
     :culture/name "Taj Mahal"
     :culture/country "IND"
     :culture/kind :heritage
     :culture/summary "17th-century ivory-white marble mausoleum in Agra built by Mughal emperor Shah Jahan for his wife Mumtaz Mahal; a UNESCO World Heritage Site designated in 1983."
     :culture/url "https://en.wikipedia.org/wiki/Taj_Mahal"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-ind culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "IND"))
                 " IND entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
