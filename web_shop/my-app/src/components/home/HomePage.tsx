import axios from "axios";
import { useEffect, useState } from "react";
import { ICategoryItem } from "./types";

const callouts = [
    {
      name: 'Desk and Office',
      description: 'Work from home accessories',
      imageSrc: 'https://tailwindui.com/img/ecommerce-images/home-page-02-edition-01.jpg',
      imageAlt: 'Desk with leather desk pad, walnut desk organizer, wireless keyboard and mouse, and porcelain mug.',
      href: '#',
    },
    {
      name: 'Self-Improvement',
      description: 'Journals and note-taking',
      imageSrc: 'https://tailwindui.com/img/ecommerce-images/home-page-02-edition-02.jpg',
      imageAlt: 'Wood table with porcelain mug, leather journal, brass pen, leather key ring, and a houseplant.',
      href: '#',
    },
    {
      name: 'Travel',
      description: 'Daily commute essentials',
      imageSrc: 'https://tailwindui.com/img/ecommerce-images/home-page-02-edition-03.jpg',
      imageAlt: 'Collection of four insulated travel bottles on wooden shelf.',
      href: '#',
    },
  ]

const HomePage = () => {
  const [list, setList] = useState<ICategoryItem[]>([]);

  useEffect(() => {
    axios.get<ICategoryItem[]>("http://localhost:8086/api/categories")
      .then(resp => { setList(resp.data); })
  }, []);
  
    return (
      <>
        <div className="bg-gray-100">
          <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
            <div className="mx-auto max-w-2xl py-16 sm:py-24 lg:max-w-none lg:py-32">
              <h2 className="text-2xl font-bold text-gray-900">Категорії</h2>

              <div className="mt-6 space-y-12 lg:grid lg:grid-cols-3 lg:gap-x-6 lg:space-y-0">
                {list.map((category) => (
                  <div key={category.id} className="group relative">
                    <div className="relative h-80 w-full overflow-hidden rounded-lg bg-white group-hover:opacity-75 sm:aspect-w-2 sm:aspect-h-1 sm:h-64 lg:aspect-w-1 lg:aspect-h-1">
                      <img
                        src={"http://localhost:8086/files/"+category.image}
                        alt={category.name}
                        className="h-full w-full object-cover object-center"
                      />
                    </div>
                    <h3 className="mt-6 text-sm text-gray-500">
                      <a href="#">
                        <span className="absolute inset-0" />
                        {category.name}
                      </a>
                    </h3>
                    <p className="text-base font-semibold text-gray-900">
                      {category.description}
                    </p>
                  </div>
                ))}
              </div>
            </div>
          </div>
        </div>
      </>
    );
}

export default HomePage;